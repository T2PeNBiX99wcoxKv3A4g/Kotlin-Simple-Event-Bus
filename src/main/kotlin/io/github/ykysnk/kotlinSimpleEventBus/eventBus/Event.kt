package io.github.ykysnk.kotlinSimpleEventBus.eventBus

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