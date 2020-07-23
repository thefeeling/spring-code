package me.daniel.enhancedperformancejpainsert.user_access_log

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.transaction.TestTransaction

@DisplayName("Repository Test")
@DataJpaTest
internal class UserAccessLogRepositoryTest {
    @Autowired
    private lateinit var repository: UserAccessLogRepository

    @Autowired
    private lateinit var entityManager: TestEntityManager

    @DisplayName("Only Insert Test")
    @Test
    fun `Only Insert 테스트`() {
        // Given
        val entity = UserAccessLog.create("userId")
        assertThat(entity.isNew).isEqualTo(true)
        // When
        val persistedEntity = repository.save(entity)
        // Then
        assertThat(persistedEntity).isNotNull
        assertThat(persistedEntity.isNew).isEqualTo(false)
    }

    @DisplayName("Select-PostLoad-isNewState-Check")
    @Test
    fun `Select-PostLoad-isNewState-Check`() {
        // Given
        if (TestTransaction.isActive()) TestTransaction.end()
        TestTransaction.start()
        val entity = entityManager.persistAndFlush(repository.save(UserAccessLog.create("userId_1")))
        TestTransaction.flagForCommit()
        TestTransaction.end()
        // When
        val target = repository.findByIdOrNull(entity.id) ?: throw RuntimeException("Not Founded Entity")
        // Then
        assertThat(target).isNotNull
        assertThat(target.isNew).isEqualTo(false)

    }


}