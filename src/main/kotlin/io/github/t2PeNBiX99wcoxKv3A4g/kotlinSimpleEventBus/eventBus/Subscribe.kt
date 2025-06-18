package io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.eventBus

/**
 * Subscribe annotation
 * 
 * @sample io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.test.TestClass
 * 
 * @param event The name of **event class name**
 * @param order The order of event callback
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Subscribe(val event: String, val order: Int = 1000)