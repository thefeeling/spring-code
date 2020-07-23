package me.daniel.enhancedperformancejpainsert.user_access_log

import me.daniel.enhancedperformancejpainsert.EnhancedPerformanceJpaInsertApplication
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [EnhancedPerformanceJpaInsertApplication::class])
internal class UserAccessLogProcessorTest {
    @Autowired
    private lateinit var processor: UserAccessLogProcessor

    @Test
    internal fun processorTest() {
        repeat(600) {
            processor.send(UserAccessLog.create("userId=$it"))
        }
        Thread.sleep(1000 * 30L)
    }
}