package me.bazzi.jpareactor;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
public class MessageController {
    private final MessageRepository repository;
    private final IMessageRepository iMessageRepository;
    private final ReactiveTransactionAdapter adapter;

    @GetMapping("/message")
    Mono<Message> get() {
        return repository.save(Message.builder().userId("userId").contents("contents").build()).log();
    }

    @GetMapping("/bulk")
    Flux<Message> list() {
        return repository.saveAll(
                Arrays.asList(
                    Message.builder().userId("userId").contents("contents").build(),
                    Message.builder().userId("userId").contents("contents").build()
                )
        ).log();
    }

    @GetMapping("/{id}")
    Mono<Message> list(@PathVariable Long id) {
        return repository.findById(id).log();
    }


    @PostMapping
    Mono<Message> create() {
        return adapter.doTransaction(() -> {
            Message message = Message.builder().userId("userId").contents("contents").build();
            return iMessageRepository.save(message);
        });
    }

}
