package me.daniel.kotlinspringbootquerydsl

import me.daniel.kotlinspringbootquerydsl.api.person.entity.Address
import me.daniel.kotlinspringbootquerydsl.api.person.entity.Person
import me.daniel.kotlinspringbootquerydsl.api.person.PersonRepository
import me.daniel.kotlinspringbootquerydsl.api.person.TestRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*

@SpringBootApplication
class KotlinSpringBootQuerydslApplication(
        private val personRepository: PersonRepository
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        val findAllBy = personRepository.findAllBy()
        for (i in 1..100) {
            val name = UUID.randomUUID().toString() + i
            personRepository.save(Person(
                    name = name,
                    address = Address(
                            city = "Seoul$i",
                            street = "Insa$i",
                            zipCode = "1111$i"
                    )
            )
            )
        }
    }
}

fun main(args: Array<String>) {
    runApplication<KotlinSpringBootQuerydslApplication>(*args)
}
