package io.github.ykysnk.kotlinSimpleEventBus.coroutineScope

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.jetbrains.annotations.ApiStatus.Internal
import kotlin.coroutines.CoroutineContext

@Internal
object EventPushScope : CoroutineScope {
    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.IO + CoroutineName("EventPush")
}