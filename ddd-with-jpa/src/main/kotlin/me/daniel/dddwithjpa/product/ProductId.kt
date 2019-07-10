package me.daniel.dddwithjpa.product

import java.io.Serializable
import javax.persistence.Embeddable

@Embeddable
class ProductId protected constructor() : Serializable {
    var id: String = ""
        protected set
}