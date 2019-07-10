package me.daniel.dddwithjpa.product

import javax.persistence.Embeddable

@Embeddable
class CategoryId protected constructor() {
	var categoryId: String = ""
		protected set
}