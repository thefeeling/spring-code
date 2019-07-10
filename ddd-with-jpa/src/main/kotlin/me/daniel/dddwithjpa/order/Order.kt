package me.daniel.dddwithjpa.order

import javax.persistence.*

@Entity
@Table(name = "purchase_order")
@Access(AccessType.FIELD)
class Order protected constructor() {
    @EmbeddedId
    lateinit var number: OrderNo
        protected set

    @Embedded
    lateinit var orderer: Orderer
        protected set

    @Embedded
    lateinit var shippingInfo: ShippingInfo
        protected set

    @Column(name = "total_amounts")
    @Convert(converter = MoneyConverter::class)
    var totalAmount: Money = Money()
        protected set

    @Column(name = "state")
    @Enumerated(value = EnumType.STRING)
    var orderState: OrderState = OrderState.PAYMENT_WAITING
        protected set

    @ElementCollection
    @CollectionTable(
        name = "order_line",
        joinColumns = [
            JoinColumn(name = "order_number")
        ]
    )
    @OrderBy(value = "line_idx")
    var orderLines: MutableList<OrderLine> = mutableListOf()
        protected set

    private fun calculateTotalAmounts() {
        this.totalAmount = Money(
            totalAmount = this.orderLines
                .map { it.price.totalAmount * it.quantity }
                .sum()
        )
    }
}

