package me.daniel.kotlinspringbootquerydsl.service

import com.querydsl.core.types.Predicate
import me.daniel.kotlinspringbootquerydsl.converter.PersonConverter
import me.daniel.kotlinspringbootquerydsl.dto.PersonDto
import me.daniel.kotlinspringbootquerydsl.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.*

@Service
class PersonService(
    private val personRepository: PersonRepository
) {

    @Autowired
    private lateinit var personConverter: PersonConverter

    fun getList(
        predicate: Predicate,
        pageable: Pageable
    ): Page<PersonDto.domainDto> {
        return personRepository.search(predicate, pageable)
            .map(personConverter::fromPerson)
    }

    fun getCustomDtoPage(
        pageable: Pageable
    ): Page<PersonDto.pageDto> {
        return personRepository.findAll(pageable)
            .map(personConverter::toPage)
    }

    fun get(id: Long): PersonDto.domainDto? {
        return personRepository.findById(id)
            .map(personConverter::fromPerson)
            .orElseThrow {
                throw RuntimeException("NotFoundResource")
            }

    }


}