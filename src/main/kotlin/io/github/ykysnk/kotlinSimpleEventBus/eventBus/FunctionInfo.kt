package io.github.ykysnk.kotlinSimpleEventBus.eventBus

import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KParameter
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.jvm.isAccessible

data class FunctionInfo(
    val func: KFunction<*>,
    val isStatic: Boolean,
    val eventClass: KClass<*>,
    val order: Int
) {
    constructor(func: KFunction<*>, isStatic: Boolean) : this(
        func = func,
        isStatic = isStatic,
        eventClass = extractEventClass(func),
        order = func.findAnnotation<Subscribe>()?.order ?: EventBus.DEFAULT_FUNC_ORDER
    )

    init {
        try {
            func.isAccessible = true
        } catch (_: Throwable) {
        }
    }

    fun isCompatibleWith(event: Event): Boolean {
        return eventClass.java.isInstance(event)
    }

    companion object {
        internal fun extractEventClass(func: KFunction<*>): KClass<*> {
            val eventParam = func.parameters.firstOrNull { it.kind == KParameter.Kind.VALUE }
            val classifier = eventParam?.type?.classifier
            return (classifier as? KClass<*>) ?: Event::class
        }
    }
}
