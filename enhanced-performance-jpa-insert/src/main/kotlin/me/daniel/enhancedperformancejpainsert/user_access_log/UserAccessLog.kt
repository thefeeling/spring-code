package me.daniel.enhancedperformancejpainsert.user_access_log

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.domain.Persistable
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "USER_ACCESS_LOGS")
class UserAccessLog protected constructor() : Persistable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id = 0L

    var userId: String = ""

    private lateinit var createdAt: LocalDateTime

    companion object {
        fun create(userId: String) = UserAccessLog().apply {
            this.userId = userId
            this.createdAt = LocalDateTime.now()
        }
    }

    @Transient
    private var isNew = true

    override fun isNew() = isNew

    @PrePersist
    @PostLoad
    internal fun markNewState() {
        this.isNew = false
    }

    override fun getId(): String? {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return "UserAccessLog(id=$id, userId='$userId', createdAt=$createdAt)"
    }


}