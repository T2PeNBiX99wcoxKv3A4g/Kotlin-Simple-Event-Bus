package io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.coroutineScope

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.jetbrains.annotations.ApiStatus.Internal
import kotlin.coroutines.CoroutineContext

@Internal
object EventPushScope : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.IO + CoroutineName("EventPush")
}