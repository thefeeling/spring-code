package me.daniel.springjpacollectionandfetures

import org.slf4j.LoggerFactory
import javax.persistence.*

@Entity
@Table(name = "boards")
@Convert(converter = TagConverter::class, attributeName = "tags")
@EntityListeners(value = [BoardListener::class])
@ExcludeDefaultListeners
@ExcludeSuperclassListeners
class Board protected constructor(){
	@Transient
	private val logger = LoggerFactory.getLogger(this::class.java)

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id = 0L
		protected set

	var title: String = ""
		protected set

	var content: String = ""
		protected set

	@OneToMany(
		mappedBy = "board",
		fetch = FetchType.LAZY,
		cascade = [CascadeType.PERSIST]
	)
	@OrderBy("id asc")
	var comments: MutableList<Comment> = mutableListOf()
		protected set

	@Convert(converter = SecretConverter::class)
	var secret: Boolean = false
		protected set

	@Column(name = "tags", columnDefinition = "text", nullable = false)
	var tags: MutableList<String> = mutableListOf()

	companion object {
		fun create(title: String, content: String): Board {
			return Board().also {
				it.title = title
				it.content = content
			}
		}
	}

	fun addComment(comment: String) = apply {
		comments.add(Comment.create(comment).also {
			it.board = this
		})
	}

	fun changeSecret() {
		this.secret = true
	}

	fun addTag(tag: String) = apply {
		if (!tag.isBlank()) this.tags.add(tag)
	}

	fun removeTag(tag: String) = apply  {
		if (!tag.isBlank()) this.tags.remove(tag)
	}

	override fun toString(): String {
		return "Board(logger=$logger, id=$id, title='$title', content='$content', comments=$comments, secret=$secret, tags=$tags)"
	}

//	@PrePersist
//	fun prePersist() {
//		logger.info("PrePersist(), {}", this)
//	}
//
//	@PostPersist
//	fun postPersist() {
//		logger.info("postPersist(), {}", this)
//	}

}