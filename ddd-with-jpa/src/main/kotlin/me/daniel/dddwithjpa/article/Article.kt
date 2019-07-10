package me.daniel.dddwithjpa.article

import javax.persistence.*

@Entity
@Table(name = "article")
@SecondaryTable(
    name = "article_content",
    pkJoinColumns = [PrimaryKeyJoinColumn(name = "id")]
)
class Article private constructor() {
    companion object {
        fun create(
            title: String = "",
            content: ArticleContent = ArticleContent()
        ) = Article().apply {
            this.title = title
            this.content = content
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
        protected set

    var title: String = ""
        protected set

    @Embedded
    @AttributeOverrides(
        value = [
            AttributeOverride(name = "content", column = Column(table = "article_content")),
            AttributeOverride(name = "contentType", column = Column(table = "article_content"))
        ]
    )
    var content: ArticleContent = ArticleContent()
        protected set

    fun modifyContent(content: ArticleContent) {
        this.content = content
    }

}

