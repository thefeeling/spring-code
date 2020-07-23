package me.daniel.enhancedperformancejpainsert

import me.daniel.enhancedperformancejpainsert.support.BatchRepositoryImpl
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories(
    repositoryBaseClass = BatchRepositoryImpl::class
)
class EnhancedPerformanceJpaInsertApplication

fun main(args: Array<String>) {
    runApplication<EnhancedPerformanceJpaInsertApplication>(*args)
}
