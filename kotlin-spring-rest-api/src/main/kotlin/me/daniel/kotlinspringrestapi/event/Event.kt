package me.daniel.kotlinspringrestapi.event

import java.time.LocalDateTime
import javax.persistence.Embeddable

@Embeddable
data class EventEnrollmentDateInfo(
    val beginEnrollmentDateTime: LocalDateTime = LocalDateTime.now(),
    val closeEnrollmentDateTime: LocalDateTime = LocalDateTime.now().plusDays(7)
)

@Embeddable
data class EventDateInfo(
    val beginEventDateTime: LocalDateTime = LocalDateTime.now().plusDays(7),
    val endEventDateTime: LocalDateTime = LocalDateTime.now().plusDays(14)
)

@Embeddable
data class EventPrice(
    val basePrice: Int = 0,
    val maxPrice: Int = 0
)

enum class EventStatus {
    DRAFT,
    PUBLISHED,
    BEGAN_ENROLLMEND,
    CLOSED_ENROLLMENT,
    STARTED,
    ENDED
}

open class Event protected constructor(){
    constructor(name: String, description: String): this() {
        this.name = name
        this.description = description
    }

    var id: Long = 0L
        protected set

    var name: String? = null
        protected set

    var description: String? = null
        protected set

    var eventStatus = EventStatus.DRAFT
        protected set

    var eventEnrollmentDateInfo: EventEnrollmentDateInfo = EventEnrollmentDateInfo()
        protected set

    var eventDateInfo: EventDateInfo = EventDateInfo()
        protected set

    var priceInfo: EventPrice = EventPrice()

    var location: String? = null
        protected set

    var limitOfEnrollment: Int = 0
        protected set

    var offline: Boolean = false
        protected set

    var free: Boolean = false
        protected set

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Event) return false
        if (id != other.id) return false
        return true
    }

    override fun hashCode() = id.hashCode()
}