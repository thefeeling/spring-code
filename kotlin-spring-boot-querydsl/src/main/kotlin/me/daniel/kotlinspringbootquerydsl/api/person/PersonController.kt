package me.daniel.kotlinspringbootquerydsl.api.person

import com.querydsl.core.types.Predicate
import me.daniel.kotlinspringbootquerydsl.api.person.entity.Person
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.querydsl.binding.QuerydslPredicate
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/persons")
class PersonController(
    private val personService: PersonService
) {

    @GetMapping
    fun dtoPage(
        @QuerydslPredicate(root = Person::class) predicate: Predicate,
        pageable: Pageable
    ) = personService.getList(predicate, pageable)

    @GetMapping("/custom")
    fun customDtoPage(
        @PageableDefault(page = 0, size = 10) pageable: Pageable
    ): Page<PersonDto.pageDto> {
        return personService.getCustomDtoPage(pageable)
    }

    @GetMapping("/{id}")
    fun customDtoPage(
        @PathVariable id: Long
    ) = personService.get(id)


}