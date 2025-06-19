@file:Suppress("unused")

package io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.eventBus

class EventReturn<T : Any>(private val data: Map<EventReturnData, T?>) : Map<EventReturnData, T?> {
    /**
     * Get first value in return values
     *
     * Return **null** if data is empty
     */
    fun first() = data.entries.minByOrNull { it.key.order }

    /**
     * Get last value in return values
     *
     * Return **null** if data is empty
     */
    fun last() = data.entries.maxByOrNull { it.key.order }

    /**
     * Get the map
     */
    fun toMap() = data

    /**
     * Returns a read-only [Set] of all key/value pairs in this map.
     */
    override val entries: Set<Map.Entry<EventReturnData, T?>> = data.entries

    /**
     * Returns a read-only [Set] of all keys in this map.
     */
    override val keys: Set<EventReturnData> = data.keys

    /**
     * Returns the number of key/value pairs in the map.
     */
    override val size: Int = data.size

    /**
     * Returns a read-only [Collection] of all values in this map. Note that this collection may contain duplicate values.
     */
    override val values: Collection<T?> = data.values

    /**
     * Returns the value corresponding to the given [key], or `null` if such a key is not present in the map.
     */
    override fun get(key: EventReturnData): T? = data[key]

    /**
     * Returns `true` if the map maps one or more keys to the specified [value].
     */
    override fun containsValue(value: T?): Boolean = data.containsValue(value)

    /**
     * Returns `true` if the map contains the specified [key].
     */
    override fun containsKey(key: EventReturnData): Boolean = data.containsKey(key)

    /**
     * Returns `true` if the map is empty (contains no elements), `false` otherwise.
     */
    override fun isEmpty(): Boolean = data.isEmpty()
}