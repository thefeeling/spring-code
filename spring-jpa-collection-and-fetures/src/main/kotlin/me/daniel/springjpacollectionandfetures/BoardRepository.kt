package me.daniel.springjpacollectionandfetures

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface BoardRepository: PagingAndSortingRepository<Board, Long> {
	@Query("select c from Comment c join c.board b where b.id = :id")
	fun findComments(
		@Param("id") id: Long,
		pageable: Pageable
	): Page<Comment>

}