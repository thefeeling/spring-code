package me.daniel.dddwithjpa.order

import javax.persistence.*

@Embeddable
data class OrderLine(
    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "productId", column = Column(name = "product_id"))
    )
    val productId: ProductId,

    @Column(name = "price")
    @Convert(converter = MoneyConverter::class)
    val price: Money,

    @Column(name = "quantity")
    val quantity: Int,

    @Column(name = "amounts")
    @Convert(converter = MoneyConverter::class)
    val amounts: Money,

    @Column(name = "line_idx")
    val lineIdx: Int
)