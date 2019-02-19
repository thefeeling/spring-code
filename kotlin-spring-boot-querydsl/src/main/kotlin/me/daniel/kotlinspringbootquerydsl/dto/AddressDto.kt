package me.daniel.kotlinspringbootquerydsl.dto

data class AddressDto(var street: String?, var zipCode: String?, var city: String?) {
    // Necessary for MapStruct
    constructor() : this(null, null, null)
}