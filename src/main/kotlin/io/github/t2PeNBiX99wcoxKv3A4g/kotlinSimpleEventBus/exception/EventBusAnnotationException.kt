package io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.exception

@Suppress("unused")
class EventBusAnnotationException(functionName: String) :
    RuntimeException("Can't find any @Subscribe annotation in $functionName function.")