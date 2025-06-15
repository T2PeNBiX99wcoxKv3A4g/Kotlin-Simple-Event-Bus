package io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.exception

@Suppress("unused")
class WrongFunctionException: Exception {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
    constructor(cause: Throwable) : super(cause)
}