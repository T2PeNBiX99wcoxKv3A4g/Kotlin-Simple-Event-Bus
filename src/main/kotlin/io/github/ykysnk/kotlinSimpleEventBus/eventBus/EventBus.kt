@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package io.github.ykysnk.kotlinSimpleEventBus.eventBus

import io.github.ykysnk.kotlinSimpleEventBus.coroutineScope.EventCollectScope
import io.github.ykysnk.kotlinSimpleEventBus.coroutineScope.EventPushScope
import io.github.ykysnk.kotlinSimpleEventBus.coroutineScope.EventSubscribeScope
import io.github.ykysnk.kotlinSimpleEventBus.exception.EventBusAnnotationException
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import org.jetbrains.annotations.ApiStatus.Internal
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.coroutines.CoroutineContext
import kotlin.reflect.KFunction
import kotlin.reflect.KParameter
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.full.staticFunctions
import kotlin.reflect.typeOf
import kotlin.time.Duration
import kotlin.time.Duration.Companion.nanoseconds
import kotlin.time.Duration.Companion.seconds
import java.time.Duration as JavaDuration

/**
 * A [EventBus] with the given configuration parameters.
 *
 * @param timeout timeout time
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
 * @param coroutineContext CoroutineContext for this EventBus instance
 */
class EventBus(
    val timeout: Duration = 3.seconds,
    replay: Int = 0,
    extraBufferCapacity: Int = 0,
    onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND,
    @JvmField val eventThrowableHandle: EventThrowableHandle,
    val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.IO + CoroutineName("EventBus")
) : AutoCloseable {
    companion object {
        const val DEFAULT_FUNC_ORDER = 1000
        const val DEFAULT_SUBSCRIBE_ORDER = 10000

        @JvmStatic
        fun createFromJava(
            timeout: JavaDuration = JavaDuration.ofSeconds(3),
            replay: Int = 0,
            extraBufferCapacity: Int = 0,
            onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND,
            eventThrowableHandle: EventThrowableHandle
        ) = EventBus(
            createDurationFromJava(timeout),
            replay,
            extraBufferCapacity,
            onBufferOverflow,
            eventThrowableHandle
        )

        @JvmStatic
        fun createFromJava(
            timeout: JavaDuration = JavaDuration.ofSeconds(3),
            eventThrowableHandle: EventThrowableHandle
        ) = EventBus(
            createDurationFromJava(timeout),
            onBufferOverflow = BufferOverflow.SUSPEND,
            eventThrowableHandle = eventThrowableHandle
        )

        @JvmStatic
        fun createFromJava(
            eventThrowableHandle: EventThrowableHandle
        ) = EventBus(3.seconds, onBufferOverflow = BufferOverflow.SUSPEND, eventThrowableHandle = eventThrowableHandle)

        private fun createDurationFromJava(timeout: JavaDuration) = timeout.toNanos().nanoseconds
    }

    private val busScope = CoroutineScope(coroutineContext)
    private val classFunctions = ConcurrentHashMap<Any, List<FunctionInfo>>()
    private val functions = CopyOnWriteArrayList<FunctionInfo>()
    private val _events = MutableSharedFlow<Event>(replay, extraBufferCapacity, onBufferOverflow)
    private val _eventReturns = MutableSharedFlow<EventReturnData>(
        replay = 64,
        extraBufferCapacity = 64,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    constructor(timeout: Duration = 3.seconds, eventThrowableHandle: EventThrowableHandle) : this(
        timeout,
        onBufferOverflow = BufferOverflow.SUSPEND,
        eventThrowableHandle = eventThrowableHandle
    )

    constructor(eventThrowableHandle: EventThrowableHandle) : this(
        3.seconds, onBufferOverflow = BufferOverflow.SUSPEND, eventThrowableHandle = eventThrowableHandle
    )

    /**
     * [SharedFlow] inside [EventBus]
     */
    @JvmField
    val events = _events.asSharedFlow()

    init {
        busScope.launch {
            events.collect {
                call(it)
            }
        }
    }

    override fun close() {
        busScope.cancel()
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
     * Non-Type Security version of [publishSuspend] for java
     *
     * @param T Return type
     * @param event The custom event
     * @param timeout timeout time
     * @param onError Error handle when error is happened
     */
    @Suppress("UNCHECKED_CAST")
    suspend fun <T : Any> publishSuspendUnSafe(
        event: Event, timeout: Duration, onError: EventThrowableHandle
    ): EventReturn<T> {
        val id = event.id
        val retList = ConcurrentHashMap<EventReturnData, T?>()
        runCatching {
            withTimeoutOrNull(timeout) {
                coroutineScope {
                    val collectJob = launch {
                        getEventReturn(id).collect {
                            retList[it] = it.returnValue as? T
                        }
                    }
                    publishSuspend(event)
                    delay(timeout)
                    collectJob.cancel()
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
     * @param timeout timeout time
     * @param onError Error handle when error is happened
     */
    suspend inline fun <reified T : Any> publishSuspend(
        event: Event, timeout: Duration, onError: EventThrowableHandle
    ): EventReturn<T> {
        val id = event.id
        val retList = ConcurrentHashMap<EventReturnData, T?>()
        runCatching {
            withTimeoutOrNull(timeout) {
                coroutineScope {
                    val collectJob = launch {
                        getEventReturn(id).collect {
                            if (it.returnValue is T) {
                                retList[it] = it.returnValue
                            }
                        }
                    }
                    publishSuspend(event)
                    delay(timeout)
                    collectJob.cancel()
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
    fun publish(event: Event) = EventPushScope.launch {
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
     * @param timeout timeout time
     * @param onError Error handle when error is happened
     */
    fun <T : Any> publishUnSafe(event: Event, timeout: JavaDuration, onError: EventThrowableHandle) =
        runBlocking(EventPushScope.coroutineContext) {
            publishSuspendUnSafe<T>(
                event,
                createDurationFromJava(timeout),
                onError
            )
        }

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
     * @param timeout timeout time
     * @param onError Error handle when error is happened
     */
    inline fun <reified T : Any> publish(event: Event, timeout: Duration, onError: EventThrowableHandle) =
        runBlocking(EventPushScope.coroutineContext) { publishSuspend<T>(event, timeout, onError) }

    /**
     * Asynchronously publish [event] to event bus and waiting return value for Java
     *
     * @param T Return type
     * @param event The custom event
     * @param timeout timeout time
     * @param onError Error handle when error is happened
     */
    fun <T : Any> publishAsync(
        event: Event,
        timeout: JavaDuration,
        onError: EventThrowableHandle
    ): java.util.concurrent.CompletableFuture<EventReturn<T>> {
        val future = java.util.concurrent.CompletableFuture<EventReturn<T>>()
        EventPushScope.launch {
            try {
                val result = publishSuspendUnSafe<T>(event, createDurationFromJava(timeout), onError)
                future.complete(result)
            } catch (e: Throwable) {
                future.completeExceptionally(e)
            }
        }
        return future
    }

    private inline fun <reified T : Event> call(event: T) {
        classFunctions.forEach { entry ->
            val matching = entry.value.filter { it.isCompatibleWith(event) }
            if (matching.isNotEmpty()) {
                val eventScope = EventCollectScope(entry.key::class.simpleName ?: "Unknown Name")
                eventScope.launch {
                    runCatching {
                        withTimeout(timeout) {
                            matching.forEach { f ->
                                val eventId = event.id
                                val ret =
                                    if (f.isStatic || f.func.parameters.none { p -> p.kind == KParameter.Kind.INSTANCE }) {
                                        f.func.call(event)
                                    } else {
                                        f.func.call(entry.key, event)
                                    }
                                val order = f.order
                                _eventReturns.emit(EventReturnData(eventId, ret, order))
                            }
                        }
                    }.getOrElse(eventThrowableHandle::handle)
                }
            }
        }

        val matchingFunctions = functions.filter { it.isCompatibleWith(event) }
        matchingFunctions.sortedBy { it.order }.forEach { funcInfo ->
            val eventScope = EventCollectScope("Function(${funcInfo.func.name})")
            eventScope.launch {
                runCatching {
                    withTimeout(timeout) {
                        val eventId = event.id
                        val ret = funcInfo.func.call(event)
                        val order = funcInfo.order
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
    ) = EventSubscribeScope.create(coroutineContext).launch {
        events.filterIsInstance<T>().collect { event ->
            runCatching {
                withTimeout(timeout) {
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
    fun subscribe(func: KFunction<*>) {
        if (!func.hasAnnotation<Subscribe>()) throw EventBusAnnotationException(func.name)
        if (!func.functionCheck()) return
        val isStatic = func.parameters.none { it.kind == KParameter.Kind.INSTANCE }
        val funcInfo = FunctionInfo(func, isStatic)
        functions.add(funcInfo)
    }

    /**
     * Add subscribe handle functions to the event bus
     *
     * ```
     * eventBus.subscribe(listOf(::testSubscribe))
     * ```
     *
     * @param funcList any function list with annotation [Subscribe]
     */
    fun subscribe(funcList: List<KFunction<*>>) = funcList.forEach { subscribe(it) }

    /**
     * Unsubscribe handle function form event bus
     *
     * @param func any function with annotation [Subscribe]
     */
    fun unsubscribe(func: KFunction<*>) {
        functions.removeIf { it.func == func }
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
            val list = mutableListOf<FunctionInfo>()

            clazz::class.declaredMemberFunctions.forEach {
                if (!it.functionCheck()) return@forEach
                list.add(FunctionInfo(it, false))
            }

            clazz::class.staticFunctions.forEach {
                if (!it.functionCheck()) return@forEach
                list.add(FunctionInfo(it, true))
            }

            list.sortBy { it.order }
            classFunctions[clazz] = list
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
        if (!hasAnnotation<Subscribe>()) return false
        val valueParams = parameters.filter { it.kind == KParameter.Kind.VALUE }
        if (valueParams.size != 1) {
            throw IllegalArgumentException("Method $name must have exactly 1 event parameter, but has ${valueParams.size}.")
        }
        val eventType = typeOf<Event>()
        if (!valueParams[0].type.isSubtypeOf(eventType)) {
            throw IllegalArgumentException("First parameter of a @Subscribe method must be an event in $name function.")
        }
        return true
    }
}