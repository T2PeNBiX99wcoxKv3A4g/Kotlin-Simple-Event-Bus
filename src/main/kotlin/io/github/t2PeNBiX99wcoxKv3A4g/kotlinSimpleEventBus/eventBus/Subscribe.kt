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
 * @param order The order of event callback, default value is **1000**. Low value will be earlier than other
 */
// TODO: Add annotationProcessor, validator
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Subscribe(val order: Int = EventBus.DEFAULT_FUNC_ORDER)