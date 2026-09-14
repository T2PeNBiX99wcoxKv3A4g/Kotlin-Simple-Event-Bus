package io.github.ykysnk.kotlinSimpleEventBus.exception

@Suppress("unused")
class EventBusAnnotationException(functionName: String) :
    RuntimeException("Can't find any @Subscribe annotation in $functionName function.")