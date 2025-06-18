package io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.eventBus

import kotlin.reflect.KType

data class EventReturn(val id: ULong, val returnValue: Any?, val returnType: KType, val order: Int)