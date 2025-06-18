@file:Suppress("unused", "UNUSED_PARAMETER")

package io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.eventBus

class EventReturn<T : Any>(private val data: Map<EventReturnData, T?>) {
    /**
     * Get first value in return values
     */
    fun first() = data.entries.minByOrNull { it.key.order }!!

    /**
     * Get last value in return values
     */
    fun last() = data.entries.maxByOrNull { it.key.order }!!

    /**
     * Performs the given [action] on each entry.
     */
    fun forEach(action: (Map.Entry<EventReturnData, T?>) -> Unit) = data::forEach

    /**
     * Get the map
     */
    fun asMap() = data
}