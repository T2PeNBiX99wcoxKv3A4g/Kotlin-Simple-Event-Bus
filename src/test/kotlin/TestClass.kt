package io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus

import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.event.SimpleTest
import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.eventBus.Subscribe

object TestClass {
    init {
        eventBus.register(this)
    }

    @Subscribe("SimpleTest")
    fun onSimpleEvent(event: SimpleTest) {
        
    }
}