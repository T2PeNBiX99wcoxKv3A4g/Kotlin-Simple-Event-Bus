package io.github.ykysnk.kotlinSimpleEventBus.test

import io.github.ykysnk.kotlinSimpleEventBus.event.SimpleEventTest
import io.github.ykysnk.kotlinSimpleEventBus.eventBus.Subscribe

object TestClass2 {
    init {
        eventBus.subscribe(::test)
    }

    @Subscribe
    fun test(event: SimpleEventTest) {
        println("subscribe Test $this")
    }
}