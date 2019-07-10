package me.daniel.dddwithjpa.order

import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = false)
class MoneyConverter: AttributeConverter<Money, Long> {
    override fun convertToDatabaseColumn(attribute: Money): Long {
        return attribute.totalAmount
    }
    override fun convertToEntityAttribute(dbData: Long): Money {
        return Money(dbData)
    }
}