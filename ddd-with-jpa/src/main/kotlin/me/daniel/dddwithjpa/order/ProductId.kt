package me.daniel.dddwithjpa.order

import javax.persistence.Embeddable

@Embeddable
class ProductId protected constructor() {
    var productId: String = ""
        protected set
}