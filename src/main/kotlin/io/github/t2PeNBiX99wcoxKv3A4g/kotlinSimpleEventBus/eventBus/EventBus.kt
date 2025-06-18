@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.eventBus

import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.coroutineScope.EventBusScope
import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.coroutineScope.EventCollectScope
import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.coroutineScope.EventPushScope
import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.coroutineScope.EventSubscribeScope
import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.exception.EventBusAnnotationException
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import org.jetbrains.annotations.ApiStatus.Internal
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.reflect.KFunction
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.jvm.isAccessible

// TODO: Handle return
/**
 * A [EventBus] with the given configuration parameters.
 *
 * @param timeoutMillis timeout time in milliseconds.
 * @param replay the number of values replayed to new subscribers (cannot be negative, defaults to zero).
 * @param extraBufferCapacity the number of values buffered in addition to `replay`.
 *   [emit][MutableSharedFlow.emit] does not suspend while there is a buffer space remaining (optional, cannot be negative, defaults to zero).
 * @param onBufferOverflow configures an [emit][MutableSharedFlow.emit] action on buffer overflow. Optional, defaults to
 *   [suspending][BufferOverflow.SUSPEND] attempts to emit a value.
 *   Values other than [BufferOverflow.SUSPEND] are supported only when `replay > 0` or `extraBufferCapacity > 0`.
 *   **Buffer overflow can happen only when there is at least one subscriber that is not ready to accept
 *   the new value.** In the absence of subscribers only the most recent [replay] values are stored and
 *   the buffer overflow behavior is never triggered and has no effect.
 * @param eventThrowableHandle handle function on any error is happened.
 */
class EventBus(
    @JvmField val timeoutMillis: Long = 3000L,
    replay: Int = 0,
    extraBufferCapacity: Int = 0,
    onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND,
    @JvmField val eventThrowableHandle: EventThrowableHandle
) {
    companion object {
        const val DEFAULT_FUNC_ORDER = 1000
        const val DEFAULT_SUBSCRIBE_ORDER = 10000
    }

    private val classFunctions = ConcurrentHashMap<Any, List<KFunction<*>>>()
    private val functions = CopyOnWriteArrayList<KFunction<*>>()
    private val _events = MutableSharedFlow<Event>(replay, extraBufferCapacity, onBufferOverflow)
    private val _eventReturns = MutableSharedFlow<EventReturnData>()

    constructor(timeoutMillis: Long = 3000L, eventThrowableHandle: EventThrowableHandle) : this(
        timeoutMillis, 0, 0, BufferOverflow.SUSPEND, eventThrowableHandle
    )

    /**
     * [SharedFlow] inside [EventBus]
     */
    @JvmField
    val events = _events.asSharedFlow()

    init {
        EventBusScope.launch(SupervisorJob()) {
            events.collect {
                call(it)
            }
        }
    }

    /**
     * This internal method, because inline method **forced** to create this
     *
     * **You should never use this, will break an event return handle**
     */
    @Internal
    suspend fun eventReturnsEmit(eventReturnData: EventReturnData) {
        _eventReturns.emit(eventReturnData)
    }

    /**
     * This internal method, because inline method **forced** to create this
     *
     * **You should never use this, will break an event return handle**
     */
    @Internal
    fun getEventReturn(id: ULong) = _eventReturns.filter { it.id == id }

    /**
     * Publish [event] to event bus in suspend function
     *
     * @param event The custom event
     */
    suspend fun publishSuspend(event: Event) {
        _events.emit(event)
    }

    /**
     * Publish [event] to event bus and waiting return value in suspend function
     *
     * @param T Return type
     * @param event The custom event
     * @param timeoutMillis timeout time in milliseconds.
     * @param onError Error handle when error is happened
     */
    suspend inline fun <reified T : Any> publishSuspend(
        event: Event, timeoutMillis: Long, onError: EventThrowableHandle
    ): EventReturn<T> {
        val id = event.id
        publishSuspend(event)
        val retList = ConcurrentHashMap<EventReturnData, T?>()
        runCatching {
            withTimeout(timeoutMillis) {
                getEventReturn(id).collect {
                    if (it.returnValue is T) {
                        retList[it] = it.returnValue
                    }
                }
            }
        }.getOrElse(onError::handle)
        return EventReturn(retList.toMap())
    }

    /**
     * Publish [event] to event bus
     *
     * @param event The custom event
     */
    fun publish(event: Event) = EventPushScope.launch(SupervisorJob()) {
        publishSuspend(event)
    }

    /**
     * Publish [event] to event bus and waiting return value
     *
     * @param T Return type
     * @param event The custom event
     * @param timeoutMillis timeout time in milliseconds.
     * @param onError Error handle when error is happened
     */
    inline fun <reified T : Any> publish(event: Event, timeoutMillis: Long, onError: EventThrowableHandle) =
        runBlocking(EventPushScope.coroutineContext) { publishSuspend<T>(event, timeoutMillis, onError) }

    private fun <T : Event> call(event: T) {
        val name = event::class.simpleName

        classFunctions.forEach {
            val eventScope = EventCollectScope(it.key::class.simpleName!!)

            eventScope.launch(SupervisorJob()) {
                runCatching {
                    withTimeout(timeoutMillis) {
                        it.value.filter { a -> a.findAnnotation<Subscribe>()?.event == name }
                            .sortedBy { a -> a.findAnnotation<Subscribe>()?.order }.forEach { f ->
                                f.isAccessible = true
                                val subscribe = f.findAnnotation<Subscribe>()
                                val eventId = event.id
                                val ret = f.call(it.key, event)
                                val order = subscribe?.order ?: DEFAULT_FUNC_ORDER

                                _eventReturns.emit(EventReturnData(eventId, ret, order))
                            }
                    }
                }.getOrElse(eventThrowableHandle::handle)
            }
        }
        functions.filter { it.findAnnotation<Subscribe>()?.event == name }
            .sortedBy { a -> a.findAnnotation<Subscribe>()?.order }.forEach {
                val eventScope = EventCollectScope("Function(${it.name})")

                eventScope.launch(SupervisorJob()) {
                    runCatching {
                        withTimeout(timeoutMillis) {
                            it.isAccessible = true
                            it.call(event)
                        }
                    }.getOrElse(eventThrowableHandle::handle)
                }
            }
    }

    /**
     * Subscribe [T] to collect
     *
     * ```
     * eventBus.subscribe<SimpleEventTest> ({
     *      // Event Handle
     * }, {
     *      // Error Handle
     * })
     * ```
     *
     * @param T The custom event
     * @param onEvent Event handle when trigger
     * @param onError Error handle when error is happened
     */
    inline fun <reified T : Event> subscribe(
        onEvent: EventHandle<T>, onError: EventThrowableHandle
    ) = EventSubscribeScope.create().launch(SupervisorJob()) {
        runCatching {
            withTimeout(timeoutMillis) {
                events.filterIsInstance<T>().collect { event ->
                    coroutineContext.ensureActive()
                    val eventId = event.id
                    val ret = onEvent.call(event)

                    eventReturnsEmit(EventReturnData(eventId, ret, DEFAULT_SUBSCRIBE_ORDER))
                }
            }
        }.getOrElse(onError::handle)
    }

    /**
     * Subscribe [T] to collect
     *
     * ```
     * eventBus.subscribe<SimpleEventTest> {
     *      // Do something
     * }
     * ```
     *
     * @param T The custom event
     * @param onEvent Event handle when trigger
     */
    inline fun <reified T : Event> subscribe(onEvent: EventHandle<T>) =
        subscribe<T>(onEvent, eventThrowableHandle::handle)

    /**
     * Add subscribe handle function to the event bus
     *
     * ```
     * eventBus.subscribe(::testSubscribe)
     * ```
     *
     * @param func any function with annotation [Subscribe]
     * @throws EventBusAnnotationException on function don't have annotation [Subscribe]
     */
    fun subscribe(func: KFunction<*>) {
        if (!func.hasAnnotation<Subscribe>()) throw EventBusAnnotationException(func.name)
        functions.add(func)
    }

    /**
     * Add subscribe handle functions to the event bus
     *
     * ```
     * eventBus.subscribe(listOf(::testSubscribe))
     * ```
     *
     * @param funcs any function list with annotation [Subscribe]
     */
    fun subscribe(funcs: List<KFunction<*>>) = funcs.filter { it.hasAnnotation<Subscribe>() }.forEach { subscribe(it) }

    /**
     * Unsubscribe handle function form event bus
     *
     * @param func any function with annotation [Subscribe]
     * @throws EventBusAnnotationException on function don't have annotation [Subscribe]
     */
    fun unsubscribe(func: KFunction<*>) {
        if (!func.hasAnnotation<Subscribe>()) throw EventBusAnnotationException(func.name)
        if (!functions.contains(func)) return
        functions.remove(func)
    }

    /**
     * Register class can be use [Subscribe] to subscribe event
     *
     * ```
     * object Sample {
     *     init {
     *         eventBus.register(this)
     *     }
     *
     *     @Subscribe("SimpleEvent")
     *     fun onSimpleEvent(event: SimpleEventTest) {
     *         // Do something
     *     }
     *
     *     @Subscribe("SimpleEvent", 900)
     *     fun onSimpleEventEarlyThenOther(event: SimpleEventTest) {
     *
     *     }
     * }
     * ```
     *
     * @param clazz any class
     */
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

    /**
     * Unregister class form event bus
     *
     * @param clazz any class
     */
    fun <T : Any> unregister(clazz: T) {
        if (!classFunctions.containsKey(clazz)) return
        EventPushScope.launch {
            classFunctions.remove(clazz)
        }
    }
}