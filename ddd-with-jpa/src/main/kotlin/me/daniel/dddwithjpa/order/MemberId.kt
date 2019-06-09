package me.daniel.dddwithjpa.order

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class MemberId(
    @Column(name = "member_id")
    val id: String
)