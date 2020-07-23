package me.daniel.enhancedperformancejpainsert.support

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean


@NoRepositoryBean
interface BatchRepository<T, ID> : JpaRepository<T, ID> {
    fun <S : T> saveInBatch(entities: Iterable<S>)
}