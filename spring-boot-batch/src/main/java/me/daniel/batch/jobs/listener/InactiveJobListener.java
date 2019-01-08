package me.daniel.batch.jobs.listener;


import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InactiveJobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("Before JOB");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("After JOB");
    }
}
