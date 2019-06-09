package me.daniel.dddwithjpa.order

import javax.persistence.Embeddable

@Embeddable
data class Address(
    val zipCode: String,
    val address1: String,
    val address2: String
)