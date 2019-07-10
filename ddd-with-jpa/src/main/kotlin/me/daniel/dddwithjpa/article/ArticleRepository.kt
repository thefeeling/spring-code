package me.daniel.dddwithjpa.article

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleRepository: JpaRepository<Article, Long>