package io.github.ykysnk.kotlinSimpleEventBus.coroutineScope

import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicLong
import kotlin.coroutines.CoroutineContext

class EventSubscribeScope private constructor(private val id: ULong, parentContext: CoroutineContext = Dispatchers.IO) :
    CoroutineScope {
    companion object {
        private val internalId = AtomicLong(0L)

        fun create(parentContext: CoroutineContext = Dispatchers.IO) =
            EventSubscribeScope(internalId.getAndIncrement().toULong(), parentContext)
    }

    override val coroutineContext: CoroutineContext =
        parentContext + SupervisorJob(parentContext[Job]) + CoroutineName("EventSubscribe($id)")
}