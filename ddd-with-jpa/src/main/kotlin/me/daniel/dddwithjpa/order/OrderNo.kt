package me.daniel.dddwithjpa.order

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class OrderNo protected constructor(): Serializable {
    @Column(name = "order_number")
    var id: Long = 0L
        protected set
}