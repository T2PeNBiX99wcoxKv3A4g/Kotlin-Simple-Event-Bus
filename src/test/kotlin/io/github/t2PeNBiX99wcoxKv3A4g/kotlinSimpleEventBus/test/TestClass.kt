@file:Suppress("unused")

package io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.test

import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.event.SimpleEventTest
import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.eventBus.Subscribe
import kotlin.test.Test

object TestClass {
    init {
        eventBus.register(this)
    }

    @Subscribe
    fun onSimpleEvent(event: SimpleEventTest) {
        println("$this onSimpleEvent $event")
    }

    @Test
    fun test() {
        println("test")
    }

    @Subscribe(900)
    fun onSimpleEventEarlyThenOther(event: SimpleEventTest) {
        println("$this onSimpleEventEarlyThenOther $event")
    }
}