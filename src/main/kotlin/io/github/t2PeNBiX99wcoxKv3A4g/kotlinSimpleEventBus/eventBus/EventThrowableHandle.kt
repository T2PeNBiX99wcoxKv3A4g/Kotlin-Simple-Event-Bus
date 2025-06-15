package io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.eventBus

fun interface EventThrowableHandle {
    fun handle(exception: Throwable)
}