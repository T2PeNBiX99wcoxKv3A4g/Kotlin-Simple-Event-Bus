package io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.coroutineScope

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

internal class EventCollectScope(private val name: String) : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.IO + CoroutineName("EventCollect($name)")
}