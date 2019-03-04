package me.daniel.kotlinspringbootquerydsl.domain

import javax.persistence.Column
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