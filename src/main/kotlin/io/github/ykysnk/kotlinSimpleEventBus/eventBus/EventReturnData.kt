package io.github.ykysnk.kotlinSimpleEventBus.eventBus

data class EventReturnData(
    val id: ULong,
    val returnValue: Any?,
    val order: Int
) : Comparable<EventReturnData> {
    override fun compareTo(other: EventReturnData): Int {
        val orderComp = order.compareTo(other.order)
        if (orderComp != 0) return orderComp
        return id.compareTo(other.id)
    }
}