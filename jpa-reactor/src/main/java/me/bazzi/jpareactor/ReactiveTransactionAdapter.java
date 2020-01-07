package me.bazzi.jpareactor;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.function.Supplier;

@RequiredArgsConstructor
@Component
@Slf4j
public class ReactiveTransactionAdapter {
    private final PlatformTransactionManager transactionManager;
    private final Scheduler scheduler;

    public <T> Mono<T> doTransaction(Supplier<T> supplier) {
        return Mono.fromCallable(() -> new TransactionTemplate(transactionManager).execute(transactionStatus -> {
            try {
                log.info("doTransaction started.");
                T t = supplier.get();
                log.info("doTransaction complete.");
                return t;
            } catch (Exception e) {
                log.error("doTransaction Failure", e);
                transactionStatus.setRollbackOnly();
                // TODO: 에러처리 레핑해야 함
                throw e;
            }
        })).subscribeOn(scheduler);
    }

    public Mono<Void> doTransactionWithoutResult(Action action) {
        return Mono.fromRunnable(() -> new TransactionTemplate(transactionManager).executeWithoutResult(transactionStatus -> {
            try {
                log.info("doTransactionWithoutResult started");
                action.run();
            } catch (Exception e) {
                log.error("doTransaction Failure", e);
                transactionStatus.setRollbackOnly();
            }
        })).subscribeOn(scheduler).then();
//        return Mono.just(new TransactionTemplate(transactionManager))
//                .flatMap(transactionTemplate -> Mono.fromRunnable(() -> transactionTemplate.executeWithoutResult(transactionStatus -> {
//                    try {
//                        action.run();
//                    } catch (Exception e) {
//                        log.error("Transaction Failure", e);
//                        transactionStatus.setRollbackOnly();
//                    }
//                })).subscribeOn(scheduler).then());
    }


}
