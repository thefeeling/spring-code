package me.daniel.kotlinspringbootquerydsl.api.person

class PersonDto {
    data class domainDto(var name: String?, var address: AddressDto?) {
        // Necessary for MapStruct
        constructor() : this(null, null)
    }
    class pageDto {
        var name: String? = null
        var street: String? = null
        var zipCode: String? = null
        var city: String? = null
    }
}