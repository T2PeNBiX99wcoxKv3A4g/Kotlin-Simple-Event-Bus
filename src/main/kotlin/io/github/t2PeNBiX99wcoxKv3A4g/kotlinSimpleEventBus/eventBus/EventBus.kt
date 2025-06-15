@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.eventBus

import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.coroutineScope.EventBusScope
import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.coroutineScope.EventCollectScope
import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.coroutineScope.EventPushScope
import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.exception.WrongFunctionException
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.launch
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.coroutines.coroutineContext
import kotlin.reflect.KFunction
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.jvm.isAccessible

class EventBus(private val eventThrowableHandle: EventThrowableHandle) {
    private val classFunctions = ConcurrentHashMap<Any, List<KFunction<*>>>()
    private val functions = CopyOnWriteArrayList<KFunction<*>>()
    private val _events = MutableSharedFlow<Event>()

    val events = _events.asSharedFlow()

    init {
        EventBusScope.launch {
            events.collect {
                call(it)
            }
        }
    }

    suspend fun publishSuspend(event: Event) {
        _events.emit(event)
    }

    fun publish(event: Event) = EventPushScope.launch {
        publishSuspend(event)
    }

    private fun <T : Event> call(event: T) {
        val name = event::class.simpleName

        classFunctions.forEach {
            val eventScope = EventCollectScope(it.key::class.simpleName!!)

            eventScope.launch {
                it.value.filter { a -> a.findAnnotation<Subscribe>()?.event == name }.forEach { f ->
                    f.isAccessible = true
                    runCatching { f.call(it.key, event) }.getOrElse(eventThrowableHandle::handle)
                }
            }
        }
        functions.filter { it.findAnnotation<Subscribe>()?.event == name }.forEach {
            val eventScope = EventCollectScope("Function(${it.name})")

            eventScope.launch {
                it.isAccessible = true
                runCatching { it.call(event) }.getOrElse(eventThrowableHandle::handle)
            }
        }
    }

    suspend inline fun <reified T : Event> subscribe(crossinline onEvent: (T) -> Unit) {
        events.filterIsInstance<T>().collect { event ->
            coroutineContext.ensureActive()
            onEvent(event)
        }
    }

    fun subscribe(func: KFunction<*>) {
        if (!func.hasAnnotation<Subscribe>()) throw WrongFunctionException(func.name)
        functions.add(func)
    }

    fun subscribe(funcs: List<KFunction<*>>) {
        funcs.filter { it.hasAnnotation<Subscribe>() }.forEach {
            subscribe(it)
        }
    }

    fun unsubscribe(func: KFunction<*>) {
        if (!func.hasAnnotation<Subscribe>()) throw WrongFunctionException(func.name)
        if (!functions.contains(func)) return
        functions.remove(func)
    }

    fun <T : Any> register(clazz: T) {
        EventPushScope.launch {
            val functions = mutableListOf<KFunction<*>>()

            clazz::class.declaredMemberFunctions.forEach {
                if (!it.hasAnnotation<Subscribe>()) return@forEach
                functions.add(it)
            }

            classFunctions[clazz] = functions
        }
    }

    fun <T : Any> unregister(clazz: T) {
        if (!classFunctions.containsKey(clazz)) return
        EventPushScope.launch {
            classFunctions.remove(clazz)
        }
    }
}