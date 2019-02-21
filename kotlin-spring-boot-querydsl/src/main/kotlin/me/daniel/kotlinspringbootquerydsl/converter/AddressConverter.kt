package me.daniel.kotlinspringbootquerydsl.converter

import me.daniel.kotlinspringbootquerydsl.domain.Address
import me.daniel.kotlinspringbootquerydsl.dto.AddressDto
import org.mapstruct.Mapper


@Mapper(componentModel = "spring")
interface AddressConverter {
    fun fromAddress(address: Address): AddressDto
}