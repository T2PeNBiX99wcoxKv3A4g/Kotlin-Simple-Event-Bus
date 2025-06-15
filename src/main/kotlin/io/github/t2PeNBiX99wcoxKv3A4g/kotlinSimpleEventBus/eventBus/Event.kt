@file:Suppress("unused")

package io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.eventBus

abstract class Event {
    private var _isCancel = false

    fun cancel() {
        isCancel = true
    }

    @Suppress("MemberVisibilityCanBePrivate")
    var isCancel: Boolean
        get() = _isCancel
        private set(value) {
            _isCancel = value
        }
}