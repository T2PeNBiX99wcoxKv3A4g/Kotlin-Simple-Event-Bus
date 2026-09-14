package io.github.ykysnk.kotlinSimpleEventBus.coroutineScope

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

internal class EventCollectScope(name: String) : CoroutineScope {
    override val coroutineContext: CoroutineContext =
        SupervisorJob() + Dispatchers.IO + CoroutineName("EventCollect($name)")
}