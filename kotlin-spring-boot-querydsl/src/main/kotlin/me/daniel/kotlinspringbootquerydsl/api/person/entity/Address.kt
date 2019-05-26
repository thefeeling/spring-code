package me.daniel.kotlinspringbootquerydsl.api.person.entity

import me.daniel.kotlinspringbootquerydsl.config.AbstractJpaPersistable
import javax.persistence.Entity

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