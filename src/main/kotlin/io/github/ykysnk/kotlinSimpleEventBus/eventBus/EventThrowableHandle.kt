package io.github.ykysnk.kotlinSimpleEventBus.eventBus

fun interface EventThrowableHandle {
    fun handle(exception: Throwable)
}