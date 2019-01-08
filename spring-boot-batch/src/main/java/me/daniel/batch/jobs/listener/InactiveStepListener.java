package me.daniel.batch.jobs.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InactiveStepListener {

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        log.info("Before Step");
    }

    @AfterStep
    public void afterStep(StepExecution stepExecution) {
        log.info("After Step");
    }

}
