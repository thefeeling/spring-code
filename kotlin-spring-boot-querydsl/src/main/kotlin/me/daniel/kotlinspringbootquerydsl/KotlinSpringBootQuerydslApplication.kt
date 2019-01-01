package me.daniel.kotlinspringbootquerydsl

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Predicate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.querydsl.binding.QuerydslPredicate
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@SpringBootApplication
@RestController
class KotlinSpringBootQuerydslApplication(
    private var addressRepository: AddressRepository,
    private var personRepository: PersonRepository
) : CommandLineRunner{
    override fun run(vararg args: String?) {
        for (i in 1..100) {
            val name = UUID.randomUUID().toString() + i
            personRepository.save(Person(
                    name = name,
                    address = Address(
                            city = "Seoul$i",
                            street = "Insa$i",
                            zipCode = "1111$i"
                    )
            ))
        }
    }

    @GetMapping("/")
    fun list(
        @QuerydslPredicate(root = Person::class) predicate: Predicate,
        @PageableDefault pageable: Pageable
    ) = personRepository.search(predicate, pageable)



}

fun main(args: Array<String>) {
    runApplication<KotlinSpringBootQuerydslApplication>(*args)
}
