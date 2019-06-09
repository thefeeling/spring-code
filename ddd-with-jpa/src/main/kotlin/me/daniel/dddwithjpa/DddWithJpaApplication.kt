package me.daniel.dddwithjpa

import me.daniel.dddwithjpa.article.Article
import me.daniel.dddwithjpa.article.ArticleContent
import me.daniel.dddwithjpa.article.ArticleRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DddWithJpaApplication: CommandLineRunner {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Autowired
    private lateinit var repository: ArticleRepository

    override fun run(vararg args: String?) {
//        val entity = repository.save(Article.create())
//        entity.modifyContent(ArticleContent(
//                content = "I'm Groot",
//                contentType = "MOVIE"
//        ))
//        val res = repository.save(entity)

    }

}

fun main(args: Array<String>) {
    runApplication<DddWithJpaApplication>(*args)
}
