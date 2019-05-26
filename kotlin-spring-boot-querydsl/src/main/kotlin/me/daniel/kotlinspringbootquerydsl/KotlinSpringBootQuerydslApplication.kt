package me.daniel.kotlinspringbootquerydsl

import me.daniel.kotlinspringbootquerydsl.domain.Address
import me.daniel.kotlinspringbootquerydsl.domain.Person
import me.daniel.kotlinspringbootquerydsl.dto.Bus
import me.daniel.kotlinspringbootquerydsl.repository.PersonRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.LocalDate
import java.util.*

class Person {
    var name: String = ""
}

@SpringBootApplication
class KotlinSpringBootQuerydslApplication (
    private val personRepository: PersonRepository
) : CommandLineRunner {

    override fun run(vararg args: String?) {
//        val busTitle = Bus.of().title
//        println(busTitle.toUpperCase())
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
