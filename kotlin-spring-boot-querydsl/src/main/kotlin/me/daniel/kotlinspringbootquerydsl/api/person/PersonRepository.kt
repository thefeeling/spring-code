package me.daniel.kotlinspringbootquerydsl.api.person

import com.querydsl.core.types.Predicate
import me.daniel.kotlinspringbootquerydsl.api.person.entity.Person
import me.daniel.kotlinspringbootquerydsl.api.person.entity.QAddress
import me.daniel.kotlinspringbootquerydsl.api.person.entity.QPerson
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer
import org.springframework.data.querydsl.binding.QuerydslBindings

interface PersonRepositoryWrapper<T> {
    fun search(predicate: Predicate, pageable: Pageable) : Page<T>
}
interface PersonRepository: JpaRepository<Person, Long>, PersonRepositoryWrapper<Person>
class PersonRepositoryImpl: QuerydslRepositorySupport(Person::class.java), PersonRepositoryWrapper<Person>, QuerydslBinderCustomizer<QPerson> {
    companion object {
        private val person = QPerson.person!!
        private val address = QAddress.address!!
    }

    override fun customize(bindings: QuerydslBindings, root: QPerson) {
        bindings.excluding(root.address)
    }

    override fun search(predicate: Predicate, pageable: Pageable): Page<Person> {
        val query = from(person).where(predicate)
                .innerJoin(person.address, address)
                .fetchJoin()
        val page = querydsl!!.applyPagination(pageable, query).fetch() ?: emptyList()
        return PageImpl(page, pageable, if (page.isEmpty()) 0L else query.fetchCount())
    }
}



