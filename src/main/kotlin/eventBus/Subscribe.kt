package io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.eventBus

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Subscribe(val event: String)