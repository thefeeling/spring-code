package me.daniel.enhancedperformancejpainsert.support

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.jpa.repository.support.JpaEntityInformation
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.io.Serializable
import javax.persistence.EntityManager

@Transactional(propagation = Propagation.REQUIRES_NEW)
class BatchRepositoryImpl<T, ID : Serializable?>(
        private val entityInformation: JpaEntityInformation<T, ID>,
        private val entityManager: EntityManager
) : SimpleJpaRepository<T, ID>(entityInformation, entityManager), BatchRepository<T, ID> {

    @Value("\${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private var batchSize: Int? = 30

    private val logger = LoggerFactory.getLogger(BatchRepository::class.java)

    override fun <S : T> saveInBatch(entities: Iterable<S>) {
        val entityTransaction = entityManager
                .entityManagerFactory
                .createEntityManager()
                .transaction
        try {
            entityTransaction.begin()
            for ((i, entity) in entities.withIndex()) {
                if (i % (batchSize ?: 30) == 0 && i > 0) {
                    logger.info("Flushing the EntityManager containing $batchSize entities ...")
                    entityTransaction.commit()
                    entityTransaction.begin()
                    entityManager.clear()
                }
                entityManager.persist(entity)
            }
            logger.info("Flushing the remaining entities ...")
            entityTransaction.commit()
        } catch (e: RuntimeException) {
            if (entityTransaction.isActive) {
                entityTransaction.rollback()
            }
            throw e
        } finally {
            entityManager.close()
        }
    }
}