package io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.test

import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.event.SimpleEventTest
import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.eventBus.Subscribe

object TestClass2 {
    init {
        eventBus.subscribe(::test)
    }

    @Subscribe
    fun test(event: SimpleEventTest) {
        println("subscribe Test $this")
    }
}