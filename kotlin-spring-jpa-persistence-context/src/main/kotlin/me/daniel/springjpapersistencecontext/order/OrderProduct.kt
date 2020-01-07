package me.daniel.springjpapersistencecontext.order

import javax.persistence.*

@Embeddable
data class OrderProduct(
	val productId: Long = 0L,
	val price: Long,
	val quantity: Int,
	val amounts: Long,
	val lineIdx: Int
)