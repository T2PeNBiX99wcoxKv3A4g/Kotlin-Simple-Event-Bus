package io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.test

import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.coroutineScope.TestScope
import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.event.SimpleEventCancel
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
            println("Send SimpleEventCancel")
            val test = eventBus.publish<Boolean>(SimpleEventCancel(), 400L) {
                println("Error: $it")
            }

            test.forEach {
                println("entry: $it")
            }

            println("test.size ${test.size}")
            println("wait 3s")
            delay(3000)
        }
    }
}

private fun subscribeAll() {
    eventBus.subscribe(::testSubscribe)
    eventBus.subscribe(::testSubscribe2)
    eventBus.subscribe(::testSubscribe3)
    eventBus.subscribe<SimpleEventTest> {
        println("subscribe<SimpleEventTest>")
    }
}

@Subscribe
private fun testSubscribe(event: SimpleEventTest) {
    println("testSubscribe $event")
}

@Subscribe
private fun testSubscribe2(event: SimpleEventCancel): Boolean {
    println("testSubscribe2 $event ${event.id}")
    return true
}

@Subscribe
private fun testSubscribe3(event: SimpleEventCancel): Int {
    println("testSubscribe3 $event ${event.id}")
    return 0
}