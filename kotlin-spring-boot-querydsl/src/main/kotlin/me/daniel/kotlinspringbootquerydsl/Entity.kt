package me.daniel.kotlinspringbootquerydsl

import org.springframework.data.util.ProxyUtils
import javax.persistence.*
import java.io.*

@MappedSuperclass
abstract class AbstractJpaPersistable<T : Serializable> {

    companion object {
        private val serialVersionUID = -5554308939380869754L
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: T? = null

    fun getId(): T? {
        return id
    }

    override fun equals(other: Any?): Boolean {
        other ?: return false

        if (this === other) return true

        if (javaClass != ProxyUtils.getUserClass(other)) return false

        other as AbstractJpaPersistable<*>

        return if (null == this.getId()) false else this.getId() == other.getId()
    }

    override fun hashCode(): Int {
        return 31
    }

    override fun toString() = "Entity of type ${this.javaClass.name} with id: $id"

}


@Entity
class Person(
    val name: String,
    @OneToOne(cascade = [(CascadeType.ALL)], orphanRemoval = true, fetch = FetchType.LAZY)
    val address: Address
) : AbstractJpaPersistable<Long>() {
    override fun toString(): String {
        return "Person(name='$name', address=$address)"
    }
}

@Entity
class Address(
    val street: String,
    val zipCode: String,
    val city: String
) : AbstractJpaPersistable<Long>() {
    override fun toString(): String {
        return "Address(street='$street', zipCode='$zipCode', city='$city')"
    }
}