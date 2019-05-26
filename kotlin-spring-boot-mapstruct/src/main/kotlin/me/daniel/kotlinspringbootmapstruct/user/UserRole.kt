package me.daniel.kotlinspringbootmapstruct.user

import com.fasterxml.jackson.annotation.JsonBackReference
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.*


@Entity(name = "T_USER_ROLES")
class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    @JsonBackReference
    var user: User? = null

    @Column(name = "role", length = 10)
    var role: Role = Role.GUEST

    @CreatedDate
    @Column(name = "createdAt", columnDefinition = "DATETIME")
    var _createdAt: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    @Column(name = "updatedAt", columnDefinition = "DATETIME")
    var _updatedAt: LocalDateTime = LocalDateTime.now()

    enum class Role {
        GUEST, ADMIN
    }
}