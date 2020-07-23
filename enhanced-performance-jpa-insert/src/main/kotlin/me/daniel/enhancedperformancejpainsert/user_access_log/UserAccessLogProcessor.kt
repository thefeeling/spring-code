package me.daniel.enhancedperformancejpainsert.user_access_log

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import reactor.core.Disposable
import reactor.core.publisher.EmitterProcessor
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import java.time.Duration
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy


@Component
class UserAccessLogProcessor(
    private val repository: UserAccessLogRepository
) {
    private val logger = LoggerFactory.getLogger(this.javaClass)
    private val emitterProcessor = EmitterProcessor.create<UserAccessLog>()
    private val flusSink = emitterProcessor.sink()
    private lateinit var disposable: Disposable

    fun send(item: UserAccessLog) {
        flusSink.next(item)
    }

    @PostConstruct
    protected fun init() {
        disposable = emitterProcessor
                .bufferTimeout(30, Duration.ofSeconds(5))
                .delaySequence(Duration.ofMillis(100))
                .limitRate(2)
                .parallel(20)
                .runOn(Schedulers.boundedElastic())
                .doOnNext {
                    logger.info("items = {}", it)
                }
                .flatMap {
                    Mono.fromCallable { repository.saveInBatch(it) }
                            .subscribeOn(Schedulers.boundedElastic())
                }
                .doOnError {
                    logger.error("Error", it)
                }
                .subscribe()
    }

    @PreDestroy
    protected fun destroy() {
        disposable.dispose()
    }

}