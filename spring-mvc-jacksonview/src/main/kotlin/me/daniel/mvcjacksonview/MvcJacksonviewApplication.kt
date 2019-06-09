package me.daniel.mvcjacksonview

import com.fasterxml.jackson.annotation.JsonFilter
import com.fasterxml.jackson.annotation.JsonView
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.converter.json.MappingJacksonValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.util.*

@SpringBootApplication
class MvcJacksonviewApplication

fun main(args: Array<String>) {
    runApplication<MvcJacksonviewApplication>(*args)
}

class Dto {
    interface Views {
        interface List
        interface Get: List
    }

    @JsonFilter("bookFilter")
    class Book (
        val isbn: String,
        val title: String,
        val content: String,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        val updatedAt: LocalDateTime = LocalDateTime.now()
    )


    class User(
        @JsonView(Views.List::class)
        val id: UUID = UUID.randomUUID(),
        @JsonView(Views.List::class)
        val email: String,
        @JsonView(Views.List::class)
        val name: String,
        @JsonView(Views.Get::class)
        val createdAt: LocalDateTime = LocalDateTime.now(),
        @JsonView(Views.Get::class)
        val updatedAt: LocalDateTime = LocalDateTime.now()
    )

}

class GetReq {
    var fields: List<String> = emptyList()
}


@RestController
@RequestMapping
class BookController {
    private companion object {
        val book: Dto.Book = Dto.Book(
            isbn = UUID.randomUUID().toString(),
            title = "Title",
            content = "Content"
        )
        val user = Dto.User(
            name = "Park",
            email = "park@gmail.com"
        )
    }

    @GetMapping("/get")
    @JsonView(Dto.Views.Get::class)
    fun jsonViewGet() = user

    @GetMapping("/list")
    @JsonView(Dto.Views.List::class)
    fun jsonViewList() = listOf(user)

    @GetMapping("/json-filter")
    fun jsonFilter(reqDto: GetReq) = MappingJacksonValue(book).apply {
        filters = SimpleFilterProvider().also {
            it.addFilter("bookFilter",
                if (reqDto.fields.isNotEmpty()) SimpleBeanPropertyFilter.filterOutAllExcept(reqDto.fields.toSet())
                else SimpleBeanPropertyFilter.serializeAll()
            )
        }
    }



}