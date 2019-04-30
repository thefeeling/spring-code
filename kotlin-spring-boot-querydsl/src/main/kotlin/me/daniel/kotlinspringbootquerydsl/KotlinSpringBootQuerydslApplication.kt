package me.daniel.kotlinspringbootquerydsl

import me.daniel.kotlinspringbootquerydsl.api.person.entity.Address
import me.daniel.kotlinspringbootquerydsl.api.person.entity.Person
import me.daniel.kotlinspringbootquerydsl.api.person.PersonRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Profile
import java.util.*

@SpringBootApplication
class KotlinSpringBootQuerydslApplication (
    private val personRepository: PersonRepository
) : CommandLineRunner {


    @Profile("test")
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
}

//@SpringBootApplication
//class KotlinSpringBootQuerydslApplication

fun main(args: Array<String>) {
    runApplication<KotlinSpringBootQuerydslApplication>(*args)
}
