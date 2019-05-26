package me.daniel.kotlinspringbootmapstruct.user

import org.hibernate.annotations.BatchSize
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.*


@Entity(name = "T_USER")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    @Column(name = "name", length = 20)
    var name: String = ""

    @Column(name = "email", length = 100)
    var email: String = ""

    @Column(name = "gender", length = 10)
    var gender: UserGender = UserGender.MALE

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @BatchSize(size = 30)
    var _roles: MutableList<UserRole> = mutableListOf()

    @Column(name = "foreigner", length = 10)
    var foreigner: Boolean = false

    @CreatedDate
    @Column(name = "createdAt", columnDefinition = "DATETIME")
    var _createdAt: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    @Column(name = "updatedAt", columnDefinition = "DATETIME")
    var _updatedAt: LocalDateTime = LocalDateTime.now()

    enum class UserGender {
        MALE, FEMALE
    }

    @get:Transient
    @delegate:Transient
    val roles: MutableList<String> by lazy {
        this._roles.map { it.role.name }.toMutableList()
    }

    @get:Transient
    @delegate:Transient
    val createdAt: LocalDateTime by lazy {
        this._createdAt
    }

    @get:Transient
    @delegate:Transient
    val updatedAt: LocalDateTime by lazy {
        this._updatedAt
    }
}