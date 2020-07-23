package me.daniel.kotlinspringbootquerydsl.api.person

import com.querydsl.jpa.impl.JPAQueryFactory
import me.daniel.kotlinspringbootquerydsl.api.person.entity.Person
import me.daniel.kotlinspringbootquerydsl.api.person.entity.QAddress
import me.daniel.kotlinspringbootquerydsl.api.person.entity.QPerson
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class TestRepository {
    companion object {
        private val person = QPerson.person!!
        private val address = QAddress.address!!
    }
    private lateinit var queryFactory: JPAQueryFactory


    @PersistenceContext
    fun setEntityManager(entityManager: EntityManager) {
        this.queryFactory = JPAQueryFactory(entityManager)
    }

    fun findAllBy(): MutableList<Person> {
        return queryFactory.from(person).fetch() as MutableList<Person>
    }



}