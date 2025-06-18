package io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.eventBus

/**
 * Subscribe annotation
 *
 * ```
 * class SimpleEvent : Event() {
 *     override fun isCancellable(): Boolean = true
 * }
 *
 * @Subscribe("SimpleEvent")
 * fun onSimpleEvent(event: SimpleEventTest) {
 *     // Do something
 * }
 * ```
 *
 * @param event The name of **event class name**
 * @param order The order of event callback
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Subscribe(val event: String, val order: Int = EventBus.DEFAULT_FUNC_ORDER)