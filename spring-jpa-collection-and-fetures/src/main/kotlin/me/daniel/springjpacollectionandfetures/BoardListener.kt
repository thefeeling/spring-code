package me.daniel.springjpacollectionandfetures

import org.slf4j.LoggerFactory
import javax.persistence.PostLoad
import javax.persistence.PostPersist
import javax.persistence.PrePersist
import javax.persistence.Transient


class BoardListener {
	private val logger = LoggerFactory.getLogger(this::class.java)

	@PostLoad
	fun postLoad(obj: Any) {
		logger.info("postLoad()")
	}

	@PrePersist
	fun prePersist(obj: Any) {
		logger.info("PrePersist()")
	}

	@PostPersist
	fun postPersist(obj: Any) {
		logger.info("postPersist()")
	}
}