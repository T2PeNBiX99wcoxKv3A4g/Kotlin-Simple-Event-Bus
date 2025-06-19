package io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.eventBus

/**
 * Event class used for event bus
 *
 * ```
 * class SimpleEvent : Event()
 * ```
 */
abstract class Event {
    private companion object {
        private var internalId: ULong = 0UL
    }

    val id = internalId++
}