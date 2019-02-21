package me.daniel.kotlinspringbootquerydsl.domain
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToOne

@Entity
class Person(
    val name: String = "DEFAULT_NAME",
    @OneToOne(cascade = [(CascadeType.ALL)], orphanRemoval = true)
    val address: Address
) : AbstractJpaPersistable<Long>() {
    override fun toString(): String {
        return "Person(name='$name', address=$address)"
    }
}