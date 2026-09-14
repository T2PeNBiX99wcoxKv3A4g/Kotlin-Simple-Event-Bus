package io.github.ykysnk.kotlinSimpleEventBus.eventBus

import kotlin.reflect.KFunction

data class FunctionInfo(val func: KFunction<*>, val isStatic: Boolean)
