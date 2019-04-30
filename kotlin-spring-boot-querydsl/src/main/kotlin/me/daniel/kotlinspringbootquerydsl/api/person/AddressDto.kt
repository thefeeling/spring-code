package me.daniel.kotlinspringbootquerydsl.api.person

data class AddressDto(var street: String?, var zipCode: String?, var city: String?) {
    // Necessary for MapStruct
    constructor() : this(null, null, null)
}