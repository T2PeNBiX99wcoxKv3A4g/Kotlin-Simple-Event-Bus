@file:Suppress("unused")

package io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.eventBus

/**
 * Event class used for event bus
 * 
 * @sample io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.event.SimpleEventTest
 */
abstract class Event {
    private var canceled = false

    /**
     * Make this event as canceled
     */
    fun cancel() {
        if (!isCancellable()) return
        canceled = true
    }

    /**
     * This event is canceled or not
     * 
     * @return is canceled or not
     */
    var isCanceled: Boolean
        get() = canceled
        private set(value) {
            canceled = value
        }

    /**
     * Is this event can be canceled or not?
     */
    protected abstract fun isCancellable(): Boolean
}