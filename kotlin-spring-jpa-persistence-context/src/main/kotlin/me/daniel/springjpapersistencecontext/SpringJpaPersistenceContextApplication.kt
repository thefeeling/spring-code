package me.daniel.springjpapersistencecontext

import me.daniel.springjpapersistencecontext.order.Order
import me.daniel.springjpapersistencecontext.order.OrderProduct
import me.daniel.springjpapersistencecontext.order.OrderRepository
import me.daniel.springjpapersistencecontext.order.OrderStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringJpaPersistenceContextApplication: CommandLineRunner {

	@Autowired
	private lateinit var repository: OrderRepository


	override fun run(vararg args: String?) {
		val orders = mutableListOf<Order>()
		for (i in (1..100).map { it.toLong() }) {
			orders.add(
				Order.doOrder(
						memberId = i,
						status = OrderStatus.PAYMENT_WAITING,
						products = listOf(
								OrderProduct(productId = i, price = 10000, quantity = 1, amounts = 10000, lineIdx = 0)
						)
				)
			)
		}
		repository.saveAll(orders)

	}

}

fun main(args: Array<String>) {
	runApplication<SpringJpaPersistenceContextApplication>(*args)
}
