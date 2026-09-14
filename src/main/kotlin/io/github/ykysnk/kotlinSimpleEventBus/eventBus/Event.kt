package io.github.ykysnk.kotlinSimpleEventBus.eventBus

import java.util.concurrent.atomic.AtomicLong

/**
 * Event class used for event bus
 *
 * ```
 * class SimpleEvent : Event()
 * ```
 */
abstract class Event {
    private companion object {
        private val internalId = AtomicLong(0L)
    }

    val id: ULong = internalId.getAndIncrement().toULong()
}