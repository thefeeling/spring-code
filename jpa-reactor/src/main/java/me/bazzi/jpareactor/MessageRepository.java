package me.bazzi.jpareactor;

import org.springframework.stereotype.Repository;
import reactor.core.scheduler.Scheduler;

@Repository
public class MessageRepository extends ReactiveRepositoryAdapter<Message, Long, IMessageRepository> {
    public MessageRepository(IMessageRepository repository, Scheduler scheduler) {
        super(repository, scheduler);
    }
}
