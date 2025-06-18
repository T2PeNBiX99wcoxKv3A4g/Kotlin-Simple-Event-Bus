package io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.eventBus

fun interface EventHandle<T> {
    fun call(obj: T): Any?
}