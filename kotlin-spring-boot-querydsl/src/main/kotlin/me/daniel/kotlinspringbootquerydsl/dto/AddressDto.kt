package me.daniel.kotlinspringbootquerydsl.dto

data class AddressDto(var street: String?, var zipCode: String?, var city: String?) {
    // Necessary for MapStruct
    constructor() : this(null, null, null)
}

val dd = AddressDto().city?.toUpperCase() ?: "UPPERCASE"
val tt = AddressDto().city?.let {
    println("Null이 아닐때 실행되요")
} ?: "Null이면 여기에요"