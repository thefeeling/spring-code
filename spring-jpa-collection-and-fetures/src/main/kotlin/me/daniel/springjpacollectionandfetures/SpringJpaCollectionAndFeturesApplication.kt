package me.daniel.springjpacollectionandfetures

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional

@SpringBootApplication
class SpringJpaCollectionAndFeturesApplication: CommandLineRunner {

	private val logger = LoggerFactory.getLogger(SpringJpaCollectionAndFeturesApplication::class.java)

	data class BoardDTO(
		var id: Long = 0L,
		var title: String = "",
		var content: String = ""
	)


	@Autowired
	lateinit var boardRepository: BoardRepository

	@Transactional(readOnly = false)
	override fun run(vararg args: String?) {
		val board = Board.create(title = "제목", content = "내용")
				.addComment("첫 코멘트")
				.addComment("두 번째 코멘트")
				.addComment("세 번째 코멘트")
				.addTag("일반")
				.run<Board, Board>(boardRepository::save)
		logger.info("board = {}", board)
		val test = boardRepository.findByIdOrNull(board.id)!!
		val comments = boardRepository.findComments(board.id, PageRequest.of(0, 10))
		logger.info("comments = {}", comments)
	}

}

fun main(args: Array<String>) {
	runApplication<SpringJpaCollectionAndFeturesApplication>(*args)
}
