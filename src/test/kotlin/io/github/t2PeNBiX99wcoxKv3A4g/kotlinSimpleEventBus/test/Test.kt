package io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.test

import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.coroutineScope.TestScope
import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.event.SimpleEventTest
import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.event.SimpleTick
import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.eventBus.EventBus
import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.eventBus.Subscribe
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

val eventBus = EventBus {
    println("Error ${it.localizedMessage} $it")
}

fun main() {
    TestClass
    TestClass2
    TestClass3()
    Test()
    subscribeAll()

    runBlocking {
        TestScope.launch {
            while (true) {
                eventBus.publish(SimpleTick())
                delay(1)
            }
        }

        while (true) {
            eventBus.publish(SimpleEventTest())
            println("publish test")
//            val test = eventBus.publish<Boolean>(SimpleEventTest(), 10000L) {
//                println("Error: $it")
//            }

//            test.forEach {
//                println("entry: $it")
//            }

            delay(3000)
        }
    }
}

private fun subscribeAll() {
    eventBus.subscribe(::testSubscribe)
    eventBus.subscribe<SimpleEventTest> {
        println("subscribe<SimpleEventTest>")
    }
}

@Subscribe
private fun testSubscribe(event: SimpleEventTest) {
    println("testSubscribe $event")
}