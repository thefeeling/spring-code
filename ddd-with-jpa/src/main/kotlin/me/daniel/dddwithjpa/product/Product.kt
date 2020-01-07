package me.daniel.dddwithjpa.product

import javax.persistence.*

@Entity
@Table(name = "product")
class Product protected constructor() {
	@EmbeddedId
	lateinit var id: ProductId
		protected set

	@ElementCollection
	@CollectionTable(
		name = "product_category",
		joinColumns = [JoinColumn(name = "product_id")]
	)
	lateinit var categoryIds: MutableSet<CategoryId>
		protected set
}