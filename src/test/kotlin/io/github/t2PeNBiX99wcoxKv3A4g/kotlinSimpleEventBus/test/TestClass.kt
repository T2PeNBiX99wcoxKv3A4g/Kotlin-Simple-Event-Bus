@file:Suppress("unused", "UNUSED_PARAMETER")

package io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.test

import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.event.SimpleEventTest
import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.eventBus.Subscribe

object TestClass {
    init {
        eventBus.register(this)
    }

    @Subscribe("SimpleEventTest")
    fun onSimpleEvent(event: SimpleEventTest) {
        // Do something
    }

    @Subscribe("SimpleEventTest", 900)
    fun onSimpleEventEarlyThenOther(event: SimpleEventTest) {

    }
}