@file:Suppress("unused")

package io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.test

import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.event.SimpleEventTest
import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.eventBus.Subscribe

object TestClass {
    init {
        eventBus.register(this)
    }

    @Subscribe
    fun onSimpleEvent(event: SimpleEventTest) {
        println("$this onSimpleEvent $event")
    }

    @Subscribe(900)
    fun onSimpleEventEarlyThenOther(event: SimpleEventTest) {
        println("$this onSimpleEventEarlyThenOther $event")
    }
}