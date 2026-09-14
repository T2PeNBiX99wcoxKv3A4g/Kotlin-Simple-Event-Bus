package io.github.ykysnk.kotlinSimpleEventBus.coroutineScope

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

class EventSubscribeScope private constructor(private val id: ULong) : CoroutineScope {
    companion object {
        private var internalId: ULong = 0UL

        fun create() = EventSubscribeScope(internalId++)
    }

    override val coroutineContext: CoroutineContext =
        SupervisorJob() + Dispatchers.IO + CoroutineName("EventSubscribe($id)")
}