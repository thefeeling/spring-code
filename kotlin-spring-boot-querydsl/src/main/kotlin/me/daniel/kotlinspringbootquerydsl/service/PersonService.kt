package me.daniel.kotlinspringbootquerydsl.service

import com.querydsl.core.types.Predicate
import me.daniel.kotlinspringbootquerydsl.converter.PersonConverter
import me.daniel.kotlinspringbootquerydsl.dto.PersonDto
import me.daniel.kotlinspringbootquerydsl.repository.PersonRepository
import org.mapstruct.factory.Mappers
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PersonService(
    private val personRepository: PersonRepository
) {

    fun getList(
        predicate: Predicate,
        pageable: Pageable
    ): Page<PersonDto.domainDto> {
        val mapper = Mappers.getMapper(PersonConverter::class.java)
        return personRepository.search(predicate, pageable)
            .map(mapper::fromPerson)
    }

    fun getCustomDtoPage(
        pageable: Pageable
//        predicate: Predicate = BooleanBuilder().value!!
    ): Page<PersonDto.pageDto> {
        val mapper = Mappers.getMapper(PersonConverter::class.java)
        return personRepository.findAll(pageable)
            .map(mapper::toPage)
    }
}