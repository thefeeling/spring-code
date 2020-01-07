package me.daniel.springjpapersistencecontext.order

enum class OrderStatus {
	PAYMENT_WAITING,
	PATMENT_COMPLETED,
	PREPARING,
	SHIPPED,
	DELIVERING,
	DELIVERY_COMPLETED
}