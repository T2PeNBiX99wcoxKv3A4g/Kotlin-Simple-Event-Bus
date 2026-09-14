package io.github.ykysnk.kotlinSimpleEventBus.test

import io.github.ykysnk.kotlinSimpleEventBus.event.SimpleEventTest
import io.github.ykysnk.kotlinSimpleEventBus.eventBus.Subscribe

class TestClass3 {
    init {
        eventBus.subscribe(::test)
    }

    @Subscribe
    fun test(event: SimpleEventTest) {
        println("subscribe Test 2 $this")
    }
}