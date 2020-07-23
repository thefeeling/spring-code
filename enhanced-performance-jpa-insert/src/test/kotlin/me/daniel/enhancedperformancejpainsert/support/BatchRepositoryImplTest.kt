package me.daniel.enhancedperformancejpainsert.support

import me.daniel.enhancedperformancejpainsert.EnhancedPerformanceJpaInsertApplication
import me.daniel.enhancedperformancejpainsert.user_access_log.UserAccessLog
import me.daniel.enhancedperformancejpainsert.user_access_log.UserAccessLogRepository
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest(classes = [EnhancedPerformanceJpaInsertApplication::class])
internal class BatchRepositoryImplTest {
    @Autowired
    private lateinit var repository: UserAccessLogRepository

    @Test
    @DisplayName("Batch Insert Test")
    internal fun `saveInBatchTest`() {
        // Given
        val list = mutableListOf<UserAccessLog>();
        for (i in 1..100) {
            list.add(UserAccessLog.create("$i"))
        }
        // When && Then
        repository.saveInBatch(list)
    }

    @Test
    @DisplayName("saveAll Test")
    internal fun `saveAllTest`() {
        // Given
        val list = mutableListOf<UserAccessLog>();
        for (i in 1..100) {
            list.add(UserAccessLog.create("$i"))
        }
        // When && Then
        repository.saveAll(list)
    }
}