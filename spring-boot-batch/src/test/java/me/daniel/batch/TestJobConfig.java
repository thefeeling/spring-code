package me.daniel.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class TestJobConfig {
    @Bean(name = "inactiveUserJobLauncher")
    public JobLauncherTestUtils inactiveUser() {
        return new JobLauncherTestUtils() {
            @Override
            @Autowired
            public void setJob(@Qualifier("InactiveUserJob") Job InactiveUserJob) {
                super.setJob(InactiveUserJob);
            }
        };
    }

    @Bean(name = "commonJobJobLauncher")
    public JobLauncherTestUtils commonJob() {
        return new JobLauncherTestUtils() {
            @Override
            @Autowired
            public void setJob(@Qualifier("commonJob") Job commonJob) {
                super.setJob(commonJob);
            }
        };
    }


}
