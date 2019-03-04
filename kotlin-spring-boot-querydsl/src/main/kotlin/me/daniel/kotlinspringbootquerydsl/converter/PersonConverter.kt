package me.daniel.kotlinspringbootquerydsl.converter

import me.daniel.kotlinspringbootquerydsl.domain.Person
import me.daniel.kotlinspringbootquerydsl.dto.PersonDto
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring", uses = [AddressConverter::class])
interface PersonConverter {
//    @Mappings(
//        value = [
//            Mapping(source = "person.name", target = "name"),
//            Mapping(source = "person.address", target = "person.address")
//        ]
//    )
    fun fromPerson(person: Person): PersonDto.domainDto

    @Mappings(
        value = [
            Mapping(source = "person.name", target = "name"),
            Mapping(source = "person.address.street", target = "street"),
            Mapping(source = "person.address.zipCode", target = "zipCode"),
            Mapping(source = "person.address.city", target = "city")
        ]
    )
    fun toPage(person: Person): PersonDto.pageDto

}