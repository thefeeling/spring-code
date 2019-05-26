package me.daniel.kotlinspringbootquerydsl.config

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

    override fun toString() = "Entity of type ${this.javaClass.name} with id: $id"
    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}


