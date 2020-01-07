package me.daniel.springjpapersistencecontext.order

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
@RequestMapping("/v1/orders")
class OrderController(
	private val orderService: OrderService
) {

	@GetMapping
	fun list(
		@PageableDefault(size = 5, page = 0)
		pageable: Pageable
	): ResponseEntity<Page<Order>> {
		return ResponseEntity.ok(orderService.getList(pageable))
	}

	@GetMapping("/{id}")
	fun get(
		@PathVariable id: Long = 0L
	): ResponseEntity<Order> {
		return ResponseEntity.ok(orderService.get(id))
	}

}