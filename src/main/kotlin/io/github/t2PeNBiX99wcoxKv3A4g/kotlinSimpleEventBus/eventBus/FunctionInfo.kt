package io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.eventBus

import kotlin.reflect.KFunction

data class FunctionInfo(val func: KFunction<*>, val isStatic: Boolean)
