package io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus

import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.event.SimpleTest
import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.eventBus.EventBus
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

val eventBus = EventBus {
    
}

fun main() {
    TestClass
    
    runBlocking { 
        eventBus.publish(SimpleTest())
        delay(1000)
    }
}