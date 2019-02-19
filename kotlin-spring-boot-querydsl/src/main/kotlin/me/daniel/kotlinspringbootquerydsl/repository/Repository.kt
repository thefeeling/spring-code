package me.daniel.kotlinspringbootquerydsl.repository

import com.querydsl.core.types.Predicate
import me.daniel.kotlinspringbootquerydsl.domain.Person
import me.daniel.kotlinspringbootquerydsl.domain.QAddress
import me.daniel.kotlinspringbootquerydsl.domain.QPerson
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport

interface PersonRepositoryWrapper<T> {
    fun search(predicate: Predicate, pageable: Pageable) : Page<T>
}
interface PersonRepository: JpaRepository<Person, Long>, PersonRepositoryWrapper<Person>
class PersonRepositoryImpl: QuerydslRepositorySupport(Person::class.java), PersonRepositoryWrapper<Person> {
    companion object {
        private val person = QPerson.person!!
        private val address = QAddress.address!!
    }

    override fun search(predicate: Predicate, pageable: Pageable): Page<Person> {
        val query = from(person).where(predicate)
                .innerJoin(person.address, address)
                .fetchJoin()
        val page = querydsl!!.applyPagination(pageable, query).fetch() ?: emptyList()
        return PageImpl(page, pageable, if (page.isEmpty()) 0L else query.fetchCount())
    }
}



