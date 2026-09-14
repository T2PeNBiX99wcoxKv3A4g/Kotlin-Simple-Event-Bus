@file:Suppress("unused")

package io.github.ykysnk.kotlinSimpleEventBus.eventBus

class EventReturn<T : Any>(private val data: Map<EventReturnData, T?>) : Map<EventReturnData, T?> by data {
    /**
     * Get first value in return values
     *
     * Return **null** if data is empty
     */
    fun first(): Map.Entry<EventReturnData, T?>? = data.entries.minByOrNull { it.key.order }

    /**
     * Get last value in return values
     *
     * Return **null** if data is empty
     */
    fun last(): Map.Entry<EventReturnData, T?>? = data.entries.maxByOrNull { it.key.order }

    /**
     * Get the map
     */
    fun toMap(): Map<EventReturnData, T?> = data
}