package me.daniel.springjpacollectionandfetures

import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = false)
class SecretConverter : AttributeConverter<Boolean, String>{
	override fun convertToDatabaseColumn(attribute: Boolean): String {
		return if (attribute) "Y" else "N"
	}

	override fun convertToEntityAttribute(dbData: String): Boolean {
		return dbData == "Y"
	}
}