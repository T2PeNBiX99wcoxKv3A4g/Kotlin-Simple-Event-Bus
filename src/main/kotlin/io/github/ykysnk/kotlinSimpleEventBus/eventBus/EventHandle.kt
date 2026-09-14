package io.github.ykysnk.kotlinSimpleEventBus.eventBus

fun interface EventHandle<T : Event> {
    fun call(obj: T): Any?
}