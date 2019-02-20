package me.daniel.kotlinspringbootquerydsl.controller

import com.querydsl.core.types.Predicate
import me.daniel.kotlinspringbootquerydsl.domain.Person
import me.daniel.kotlinspringbootquerydsl.service.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.querydsl.binding.QuerydslPredicate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/persons")
class PersonController {

    @Autowired
    private lateinit var personService: PersonService


    @GetMapping
    fun dtoPage(
        @QuerydslPredicate(root = Person::class) predicate: Predicate,
        pageable: Pageable
    ) = personService.getList(predicate, pageable)

    @GetMapping("/custom")
    fun customDtoPage(
        pageable: Pageable
    ) = personService.getCustomDtoPage(pageable)

}