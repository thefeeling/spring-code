package me.daniel.batch.jobs;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Configuration
public class CommonJobConfig {
    private static final int CHUNK_SIZE = 10;

    @Data
    private static class CommonJob {
        private String name;
        private String title;
        private String description;

        @Builder
        public CommonJob(String name, String title, String description) {
            this.name = name;
            this.title = title;
            this.description = description;
        }
    }

    @Slf4j
    @Component
    static class CommonJobTasklet1 implements Tasklet {

        @Override
        public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
            log.info("CommonJobTasklet1.execute");
            if (new Random().nextInt() > 0) {
                return RepeatStatus.CONTINUABLE;
            }
            return RepeatStatus.FINISHED;
        }
    }

    @Slf4j
    @Component
    static class CommonJobTasklet2 implements Tasklet {

        @Override
        public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
            log.info("CommonJobTasklet2.execute");
            return RepeatStatus.FINISHED;
        }
    }

    @Bean(name = "commonJob")
    public Job commonJob(
        CommonJobTasklet1 tasklet1,
        CommonJobTasklet1 tasklet2,
        JobRepository jobRepository
    ) {
        return new JobBuilderFactory(jobRepository)
            .get("commonJob")
            .start(new StepBuilder("commonStep1").tasklet(tasklet1).build())
            .on("COMPLETED").to(new StepBuilder("commonStep2").tasklet(tasklet2).build())
            .on("*").fail()
            .end()
            .build();
    }


//    @Bean
//    public Step commonStep1(StepBuilderFactory stepBuilderFactory) {
//        return stepBuilderFactory
//            .get("commonStep1")
//            .<CommonJob, CommonJob>chunk(CHUNK_SIZE)
//            .reader(() -> CommonJob.builder()
//                .name("stepName")
//                .title("title")
//                .description("description")
//                .build()
//            )
//            .processor((ItemProcessor<CommonJob, CommonJob>) item -> item)
//            .writer(items -> items.stream().forEach(o -> log.info("{}", o)))
//            .build();
//    }




}
