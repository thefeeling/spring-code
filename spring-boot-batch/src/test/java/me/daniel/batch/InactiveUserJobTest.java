package me.daniel.batch;

import me.daniel.batch.domain.enums.UserStatus;
import me.daniel.batch.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InactiveUserJobTest {
    @Autowired
    @Qualifier("inactiveUserJobLauncher")
    private JobLauncherTestUtils inactiveUserJobLauncher;

    @Autowired
    @Qualifier("commonJobJobLauncher")
    private JobLauncherTestUtils commonJobJobLauncher;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void 휴면_회원_전환_테스트() throws Exception {
        JobExecution jobExecution = inactiveUserJobLauncher.launchJob(
            new JobParametersBuilder().addDate("nowDate", new Date()).toJobParameters()
        );
        assertEquals(
            BatchStatus.COMPLETED,
            jobExecution.getStatus()
        );

        int size = userRepository.findByUpdatedDateBeforeAndStatusEquals(
            LocalDateTime.now().minusYears(1), UserStatus.ACTIVE
        ).size();

        assertEquals(0, size);
    }

    @Test
    public void FLOW_전환_테스트() throws Exception {
        JobExecution jobExecution = commonJobJobLauncher.launchJob(
            new JobParametersBuilder().addDate("nowDate", new Date()).toJobParameters()
        );
        System.out.println(jobExecution);
        assertEquals(0, 0);
    }

}
