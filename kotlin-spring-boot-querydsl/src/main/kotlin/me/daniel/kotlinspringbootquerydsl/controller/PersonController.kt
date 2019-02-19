package me.daniel.kotlinspringbootquerydsl.controller

import com.querydsl.core.types.Predicate
import me.daniel.kotlinspringbootquerydsl.converter.PersonConverter
import me.daniel.kotlinspringbootquerydsl.domain.Person
import me.daniel.kotlinspringbootquerydsl.dto.PersonDto
import me.daniel.kotlinspringbootquerydsl.repository.AddressRepository
import me.daniel.kotlinspringbootquerydsl.repository.PersonRepository
import org.mapstruct.factory.Mappers
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.querydsl.binding.QuerydslPredicate
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/persons")
class PersonController(
    private val addressRepository: AddressRepository,
    private val personRepository: PersonRepository
) {
    @GetMapping
    fun dtoPage(
        @QuerydslPredicate(root = Person::class) predicate: Predicate,
        @PageableDefault pageable: Pageable
    ): Page<PersonDto.domainDto> {
        val mapper = Mappers.getMapper(PersonConverter::class.java)
        return personRepository.search(predicate, pageable)
                .map(mapper::fromPerson)
    }

    @GetMapping("/custom")
    fun customDtoPage(
        @QuerydslPredicate(root = Person::class) predicate: Predicate,
        @PageableDefault pageable: Pageable
    ): Page<PersonDto.pageDto> {
        val mapper = Mappers.getMapper(PersonConverter::class.java)
        return personRepository.search(predicate, pageable)
                .map(mapper::toPage)
    }

}