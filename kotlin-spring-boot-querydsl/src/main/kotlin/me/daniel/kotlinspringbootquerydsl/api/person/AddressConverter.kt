package me.daniel.kotlinspringbootquerydsl.api.person

import me.daniel.kotlinspringbootquerydsl.api.person.entity.Address
import org.mapstruct.Mapper


@Mapper(componentModel = "spring")
interface AddressConverter {
    fun fromAddress(address: Address): AddressDto
}