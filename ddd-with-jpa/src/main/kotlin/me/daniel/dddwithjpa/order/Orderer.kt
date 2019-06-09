package me.daniel.dddwithjpa.order

import javax.persistence.*

@Embeddable
data class Orderer(
    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "id", column = Column(name = "orderer_id"))
    )
    val memberId: MemberId,

    @Column(name = "orderer_name")
    val name: String
)