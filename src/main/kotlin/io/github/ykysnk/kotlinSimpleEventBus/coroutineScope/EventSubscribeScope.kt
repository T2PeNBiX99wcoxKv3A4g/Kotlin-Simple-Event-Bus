package io.github.ykysnk.kotlinSimpleEventBus.coroutineScope

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import java.util.concurrent.atomic.AtomicLong
import kotlin.coroutines.CoroutineContext

class EventSubscribeScope private constructor(private val id: ULong, parentContext: CoroutineContext = Dispatchers.IO) : CoroutineScope {
    companion object {
        private val internalId = AtomicLong(0L)

        fun create(parentContext: CoroutineContext = Dispatchers.IO) =
            EventSubscribeScope(internalId.getAndIncrement().toULong(), parentContext)
    }

    override val coroutineContext: CoroutineContext =
        parentContext + SupervisorJob(parentContext[Job]) + CoroutineName("EventSubscribe($id)")
}