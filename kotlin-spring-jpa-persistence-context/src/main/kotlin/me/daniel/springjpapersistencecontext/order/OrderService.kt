package me.daniel.springjpapersistencecontext.order

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class OrderService(
	private val orderRepository: OrderRepository
) {
	fun getList(pageable: Pageable): Page<Order> {
		val list = orderRepository.findAll(pageable)
//		for (order in list) {
//			// 실제 값을 사용할 때 프록시 객체가 초기화 된다.
//			order.orderProducts.forEach { it.amounts }
//		}
		for (order in list) {
			// 실제 값을 사용할 때 프록시 객체가 초기화 된다.
			order.orderProducts.forEach { it.amounts }
		}
		return list
	}

	fun get(id: Long): Order {
		val order = (orderRepository.findByIdOrNull(id)
				?: throw NotExistsOrderException("존재하지 않은 주문입니다."))
		// 실제 값을 사용할 때 프록시 객체가 초기화 된다.
		order.orderProducts.forEach { it.amounts }
		return order
	}
}