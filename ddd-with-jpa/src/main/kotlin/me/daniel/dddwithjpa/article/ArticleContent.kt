package me.daniel.dddwithjpa.article

import javax.persistence.Embeddable

@Embeddable
data class ArticleContent (
    val content: String = "",
    val contentType: String = ""
)