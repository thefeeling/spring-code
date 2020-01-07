package me.daniel.springjpacollectionandfetures

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = false)
class TagConverter: AttributeConverter<MutableList<String>, String> {
	override fun convertToDatabaseColumn(attribute: MutableList<String>?): String {
		return jacksonObjectMapper().writeValueAsString(attribute)
	}

	override fun convertToEntityAttribute(dbData: String?): MutableList<String> {
		return dbData?.let {
			jacksonObjectMapper().readValue<MutableList<String>>(dbData, object : TypeReference<MutableList<String>>(){})
		} ?: mutableListOf()
	}
}