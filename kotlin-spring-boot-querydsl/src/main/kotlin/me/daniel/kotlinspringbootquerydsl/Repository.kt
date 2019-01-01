package me.daniel.kotlinspringbootquerydsl

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Predicate
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.stereotype.Repository


// Usage 1)
//interface PersonRepository : JpaRepository<Person, Long>, QuerydslPredicateExecutor<Person>

interface PersonRepositoryWrapper<T> {
    fun search(predicate: Predicate, pageable: Pageable) : Page<T>
}

interface PersonRepository<T>: JpaRepository<T, Long>, PersonRepositoryWrapper<T>

class PersonRepositoryImpl: QuerydslRepositorySupport(Person::class.java), PersonRepositoryWrapper<Person> {
    override fun search(predicate: Predicate, pageable: Pageable): Page<Person> {
        val person = QPerson.person
        val query = from(person).where(predicate)
        val persons = querydsl!!.applyPagination(pageable, query).fetch()
        val count = query.fetchCount()
        return PageImpl(persons, pageable, count)
    }
}

@Repository
interface AddressRepository : JpaRepository<Address, Long>, QuerydslPredicateExecutor<Address>



