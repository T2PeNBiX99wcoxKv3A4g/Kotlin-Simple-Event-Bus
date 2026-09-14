package io.github.ykysnk.kotlinSimpleEventBus.coroutineScope

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

object TestScope: CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.IO + CoroutineName("EventBus")
}