package me.daniel.dddwithjpa.order

import javax.persistence.*

@Embeddable
data class ShippingInfo(
    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "zipCode", column = Column(name = "shipping_zipcode")),
        AttributeOverride(name = "address1", column = Column(name = "shipping_addr1")),
        AttributeOverride(name = "address2", column = Column(name = "shipping_addr2"))
    )
    val address: Address,

    @Column(name = "shipping_message")
    val message: String
)