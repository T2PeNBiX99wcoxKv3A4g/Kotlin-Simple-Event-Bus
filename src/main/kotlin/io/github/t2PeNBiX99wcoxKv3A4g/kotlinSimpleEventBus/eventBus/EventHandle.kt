package io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.eventBus

fun interface EventHandle<T : Event> {
    fun call(obj: T): Any?
}