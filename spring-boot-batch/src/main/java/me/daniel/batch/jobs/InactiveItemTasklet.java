package me.daniel.batch.jobs;

import lombok.AllArgsConstructor;
import me.daniel.batch.domain.User;
import me.daniel.batch.domain.enums.UserStatus;
import me.daniel.batch.repository.UserRepository;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class InactiveItemTasklet implements Tasklet {

    private UserRepository userRepository;


    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        Date date = (Date) chunkContext.getStepContext().getJobParameters().get("nowDate");
        LocalDateTime now = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        List<User> inactiveUsers = userRepository.findByUpdatedDateBeforeAndStatusEquals(
            now.minusYears(1), UserStatus.ACTIVE
        ).stream().map(User::setInactive).collect(Collectors.toList());
        userRepository.saveAll(inactiveUsers);
        return RepeatStatus.FINISHED;
    }
}
