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
import kotlin.reflect.full.*
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.jvm.javaType
import kotlin.reflect.typeOf

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

    private val classFunctions = ConcurrentHashMap<Any, List<FunctionInfo>>()
    private val functions = CopyOnWriteArrayList<KFunction<*>>()
    private val _events = MutableSharedFlow<Event>(replay, extraBufferCapacity, onBufferOverflow)
    private val _eventReturns = MutableSharedFlow<EventReturnData>()

    constructor(timeoutMillis: Long = 3000L, eventThrowableHandle: EventThrowableHandle) : this(
        timeoutMillis, 0, 0, BufferOverflow.SUSPEND, eventThrowableHandle
    )

    constructor(eventThrowableHandle: EventThrowableHandle) : this(
        3000L, 0, 0, BufferOverflow.SUSPEND, eventThrowableHandle
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
    fun getEventReturn(id: ULong) = _eventReturns.filter {
        it.id == id
    }

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
     * Non-Type Security version of [publishSuspend] for java
     *
     * @param T Return type
     * @param event The custom event
     * @param timeoutMillis timeout time in milliseconds.
     * @param onError Error handle when error is happened
     */
    @Suppress("UNCHECKED_CAST")
    suspend fun <T : Any> publishSuspendUnSafe(
        event: Event, timeoutMillis: Long, onError: EventThrowableHandle
    ): EventReturn<T> {
        val id = event.id
        publishSuspend(event)
        val retList = ConcurrentHashMap<EventReturnData, T?>()
        runCatching {
            withTimeoutOrNull(timeoutMillis) {
                getEventReturn(id).collect {
                    retList[it] = it.returnValue as T
                }
            }
        }.getOrElse(onError::handle)
        return EventReturn(retList.toMap())
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
            withTimeoutOrNull(timeoutMillis) {
                getEventReturn(id).collect {
                    if (it.returnValue !is T) return@collect
                    retList[it] = it.returnValue
                }
            }
        }.getOrElse(onError::handle)
        return EventReturn(retList.toMap())
    }

    /**
     * Publish [event] to event bus
     *
     * ```
     * eventBus.publish(SampleEvent())
     * ```
     *
     * @param event The custom event
     */
    fun publish(event: Event) = EventPushScope.launch(SupervisorJob()) {
        publishSuspend(event)
    }

    /**
     * Publish [event] to event bus and waiting return value
     *
     * Non-Type Security version of [publish] for java
     *
     * ```
     * val retList = eventBus.publish<Boolean>(SampleEvent(), 5000L) {
     *      // Error Handle
     * }
     * ```
     *
     * @param T Return type
     * @param event The custom event
     * @param timeoutMillis timeout time in milliseconds.
     * @param onError Error handle when error is happened
     */
    fun <T : Any> publishUnSafe(event: Event, timeoutMillis: Long, onError: EventThrowableHandle) =
        runBlocking(EventPushScope.coroutineContext) { publishSuspendUnSafe<T>(event, timeoutMillis, onError) }

    /**
     * Publish [event] to event bus and waiting return value
     *
     * ```
     * val retList = eventBus.publish<Boolean>(SampleEvent(), 5000L) {
     *      // Error Handle
     * }
     * ```
     *
     * @param T Return type
     * @param event The custom event
     * @param timeoutMillis timeout time in milliseconds.
     * @param onError Error handle when error is happened
     */
    inline fun <reified T : Any> publish(event: Event, timeoutMillis: Long, onError: EventThrowableHandle) =
        runBlocking(EventPushScope.coroutineContext) { publishSuspend<T>(event, timeoutMillis, onError) }

    private inline fun <reified T : Event> call(event: T) {
        classFunctions.forEach {
            val eventScope = EventCollectScope(it.key::class.simpleName!!)

            eventScope.launch(SupervisorJob()) {
                runCatching {
                    withTimeout(timeoutMillis) {
                        it.value.filter { a -> if (a.func.parameters.size > 1) a.func.parameters[1].type.javaType.typeName == event::class.qualifiedName else a.func.parameters[0].type.javaType.typeName == event::class.qualifiedName }
                            .sortedBy { a -> a.func.findAnnotation<Subscribe>()?.order }.forEach { f ->
                                f.func.isAccessible = true
                                val subscribe = f.func.findAnnotation<Subscribe>()
                                val eventId = event.id
                                val ret = if (f.isStatic) f.func.call(event) else f.func.call(it.key, event)
                                val order = subscribe?.order ?: DEFAULT_FUNC_ORDER

                                _eventReturns.emit(EventReturnData(eventId, ret, order))
                            }
                    }
                }.getOrElse(eventThrowableHandle::handle)
            }
        }
        functions.filter { if (it.parameters.size > 1) it.parameters[1].type.javaType.typeName == event::class.qualifiedName else it.parameters[0].type.javaType.typeName == event::class.qualifiedName }
            .sortedBy { a -> a.findAnnotation<Subscribe>()?.order }.forEach {
                val eventScope = EventCollectScope("Function(${it.name})")

                eventScope.launch(SupervisorJob()) {
                    runCatching {
                        withTimeout(timeoutMillis) {
                            it.isAccessible = true
                            val subscribe = it.findAnnotation<Subscribe>()
                            val eventId = event.id
                            val ret = it.call(event)
                            val order = subscribe?.order ?: DEFAULT_FUNC_ORDER

                            _eventReturns.emit(EventReturnData(eventId, ret, order))
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
    // TODO: Add java version
    inline fun <reified T : Event> subscribe(
        onEvent: EventHandle<T>, onError: EventThrowableHandle
    ) = EventSubscribeScope.create().launch(SupervisorJob()) {
        events.filterIsInstance<T>().collect { event ->
            runCatching {
                withTimeout(timeoutMillis) {
                    coroutineContext.ensureActive()
                    val eventId = event.id
                    val ret = onEvent.call(event)

                    eventReturnsEmit(EventReturnData(eventId, ret, DEFAULT_SUBSCRIBE_ORDER))
                }
            }.getOrElse(onError::handle)
        }
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
    // TODO: Handle java method
    fun subscribe(func: KFunction<*>) {
        if (!func.functionCheck()) return
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
    fun subscribe(funcs: List<KFunction<*>>) = funcs.filter { it.functionCheck() }.forEach { subscribe(it) }

    /**
     * Unsubscribe handle function form event bus
     *
     * @param func any function with annotation [Subscribe]
     * @throws EventBusAnnotationException on function don't have annotation [Subscribe]
     */
    fun unsubscribe(func: KFunction<*>) {
        if (!func.functionCheck()) return
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
            val functions = mutableListOf<FunctionInfo>()

            clazz::class.declaredMemberFunctions.forEach {
                if (!it.functionCheck()) return@forEach
                functions.add(FunctionInfo(it, false))
            }

            clazz::class.staticFunctions.forEach {
                if (!it.functionCheck()) return@forEach
                functions.add(FunctionInfo(it, true))
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

    private fun KFunction<*>.functionCheck(): Boolean {
        if (!hasAnnotation<Subscribe>()) throw EventBusAnnotationException(name)
        if (parameters.isEmpty() || parameters.size > 2) throw IllegalArgumentException("Too many parameter, only 1 parameter is allow. in $name function.")
        val eventType = typeOf<Event>()
        if (if (parameters.size > 1) !parameters[1].type.isSubtypeOf(eventType) else !parameters[0].type.isSubtypeOf(
                eventType
            )
        ) throw IllegalArgumentException("First parameter of a @Subscribe method must be an event. in $name function.")
        return true
    }
}