package me.daniel.springjpapersistencecontext.order

import org.hibernate.annotations.BatchSize
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "orders")
class Order protected constructor() {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long = 0L
		protected set

	@Column(nullable = false, updatable = false)
	var memberId: Long = 0L
		protected set

	@Enumerated(value = EnumType.STRING)
	@Column(length = 20, nullable = false)
	var status: OrderStatus = OrderStatus.PAYMENT_WAITING
		protected set

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(
		name = "order_products",
		joinColumns = [
			JoinColumn(name = "id")
		]
	)
	@AttributeOverrides(
		value = [
			AttributeOverride(name = "productId", column = Column(name = "product_id")),
			AttributeOverride(name = "price", column = Column(name = "price")),
			AttributeOverride(name = "quantity", column = Column(name = "quantity")),
			AttributeOverride(name = "amounts", column = Column(name = "amounts")),
			AttributeOverride(name = "line_idx", column = Column(name = "line_idx"))
		]
	)
	@BatchSize(size = 10)
	var orderProducts: MutableList<OrderProduct> = mutableListOf()
		protected set

	@CreationTimestamp
	@Column(nullable = false)
	var createdAt: LocalDateTime = LocalDateTime.now()
		protected set

	@UpdateTimestamp
	@Column(nullable = false)
	var updatedAt: LocalDateTime = LocalDateTime.now()
		protected set

	companion object {
		fun doOrder(
			memberId: Long,
			status: OrderStatus,
			products: List<OrderProduct>
		): Order {
			if (status !in listOf(OrderStatus.PAYMENT_WAITING, OrderStatus.PATMENT_COMPLETED))
				throw IllegalArgumentException("올바른 주문 상태 값이 아닙니다.")
			if (products.isEmpty())
				throw IllegalArgumentException("최소 한개 이상의 구매 상품을 포함해야 합니다.")
			return Order().also {
				it.memberId = memberId
				it.status = status
				it.orderProducts = products.toMutableList()
			}
		}


	}

}