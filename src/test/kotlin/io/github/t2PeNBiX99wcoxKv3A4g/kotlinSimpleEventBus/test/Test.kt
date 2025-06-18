@file:Suppress("UNUSED_PARAMETER")

package io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.test

import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.event.SimpleEventTest
import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.eventBus.EventBus
import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.eventBus.Subscribe
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

val eventBus = EventBus {

}

fun main() {
    TestClass
    subscribeAll()

    runBlocking {
        eventBus.publish(SimpleEventTest())
        delay(1000)
    }
}

private fun subscribeAll() {
    eventBus.subscribe(::testSubscribe)
    eventBus.subscribe(listOf(::testSubscribe))
    eventBus.subscribe<SimpleEventTest> { 
        // Do something
    }
}

@Subscribe("SimpleEventTest")
private fun testSubscribe(event: SimpleEventTest) {

}