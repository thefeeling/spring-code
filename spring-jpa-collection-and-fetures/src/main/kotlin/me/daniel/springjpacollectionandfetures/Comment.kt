package me.daniel.springjpacollectionandfetures

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "comments")
class Comment protected constructor() {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id = 0L

	var comment: String = ""

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "boards_id")
	lateinit var board: Board

	@Enumerated(value = EnumType.STRING)
	var status: CommentStatus = CommentStatus.ACTIVE

	@CreationTimestamp
	var createdAt: LocalDateTime = LocalDateTime.now()

	var removedAt: LocalDateTime? = null

	companion object {
		fun create(comment: String): Comment {
			return Comment().also {
				it.comment = comment
			}
		}
	}
}

