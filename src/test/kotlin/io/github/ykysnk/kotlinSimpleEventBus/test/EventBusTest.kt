package io.github.ykysnk.kotlinSimpleEventBus.test

import io.github.ykysnk.kotlinSimpleEventBus.event.SimpleEventTest
import io.github.ykysnk.kotlinSimpleEventBus.eventBus.Event
import io.github.ykysnk.kotlinSimpleEventBus.eventBus.EventBus
import io.github.ykysnk.kotlinSimpleEventBus.eventBus.Subscribe
import io.github.ykysnk.kotlinSimpleEventBus.exception.EventBusAnnotationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.concurrent.thread
import kotlin.time.Duration.Companion.milliseconds
import org.junit.jupiter.api.Test as JUnitTest

class EventBusTest {

    @JUnitTest
    fun testEventIdThreadSafety() {
        val count = 1000
        val ids = ConcurrentHashMap.newKeySet<ULong>()
        val threads = (1..10).map {
            thread {
                repeat(count / 10) {
                    val event = SimpleEventTest()
                    ids.add(event.id)
                }
            }
        }
        threads.forEach { it.join() }
        assertEquals(count, ids.size, "All generated event IDs must be unique across threads")
    }

    @JUnitTest
    fun testSubscribeMissingAnnotationThrows() {
        val bus = EventBus { }
        assertThrows<EventBusAnnotationException> {
            bus.subscribe(::unannotatedFunction)
        }
    }

    @JUnitTest
    fun testBasicPublishAndSubscribe() = runBlocking {
        val bus = EventBus { }
        val received = CopyOnWriteArrayList<SimpleEventTest>()

        val job = bus.subscribe<SimpleEventTest> {
            received.add(it)
        }
        delay(50.milliseconds)

        val event = SimpleEventTest()
        bus.publishSuspend(event)
        delay(100.milliseconds)

        assertEquals(1, received.size)
        assertEquals(event.id, received[0].id)
        job.cancel()
    }

    @JUnitTest
    fun testClassRegistrationAndOrdering() = runBlocking {
        val bus = EventBus { }
        val listener = TestOrderListener()
        bus.register(listener)

        var res: io.github.ykysnk.kotlinSimpleEventBus.eventBus.EventReturn<String>? = null
        for (i in 1..50) {
            val r = bus.publishSuspend<String>(SimpleEventTest(), 200.milliseconds) {}
            if (r.size == 2) {
                res = r
                break
            }
            delay(20.milliseconds)
        }
        assertNotNull(res)
        val finalRes = res!!
        assertEquals(2, finalRes.size)
        assertEquals("early", finalRes.first()?.value)
        assertEquals("late", finalRes.last()?.value)
    }

    @JUnitTest
    fun testPolymorphismEventDispatch() = runBlocking {
        val bus = EventBus { }
        var baseEventReceived = false

        val job = bus.subscribe<Event> {
            baseEventReceived = true
        }
        delay(50.milliseconds)

        bus.publishSuspend(SimpleEventTest())
        delay(100.milliseconds)

        assertTrue(baseEventReceived, "Subscriber for base Event should receive child SimpleEventTest")
        job.cancel()
    }

    @JUnitTest
    fun testUnsubscribeAndUnregister() = runBlocking {
        val bus = EventBus { it.printStackTrace() }
        val listener = TestUnregisterListener()
        bus.register(listener)
        
        // Wait until async registration completes
        var registered = false
        for (i in 1..50) {
            val res = bus.publishSuspend<Unit>(SimpleEventTest(), 100.milliseconds) {}
            if (res.isNotEmpty()) {
                registered = true
                break
            }
            delay(20.milliseconds)
        }
        assertTrue(registered, "Listener should be registered and invoked")
        val initialCount = listener.count

        bus.unregister(listener)
        delay(100.milliseconds)

        bus.publishSuspend(SimpleEventTest())
        delay(100.milliseconds)
        assertEquals(initialCount, listener.count, "Count should not increase after unregister")
    }

    @JUnitTest
    fun testEventBusClose() = runBlocking {
        val bus = EventBus { }
        var received = 0
        bus.subscribe<SimpleEventTest> {
            received++
        }
        delay(50.milliseconds)

        bus.publishSuspend(SimpleEventTest())
        delay(100.milliseconds)
        assertEquals(1, received)

        bus.close()
        bus.publishSuspend(SimpleEventTest())
        delay(100.milliseconds)
        assertEquals(1, received, "No events should be processed after close")
    }

    @JUnitTest
    fun testStandaloneFunctionSubscribeAndUnsubscribe() = runBlocking {
        val bus = EventBus { }
        topLevelReceived = 0

        bus.subscribe(::standaloneAnnotatedFunction)
        bus.publishSuspend(SimpleEventTest())
        delay(100.milliseconds)
        assertEquals(1, topLevelReceived)

        bus.unsubscribe(::standaloneAnnotatedFunction)
        bus.publishSuspend(SimpleEventTest())
        delay(100.milliseconds)
        assertEquals(1, topLevelReceived, "Should not receive after unsubscribe")
    }

    @JUnitTest
    fun testPublishAsync() {
        val bus = EventBus { }
        bus.subscribe(::standaloneAnnotatedFunctionWithReturn)

        val future = bus.publishAsync<String>(SimpleEventTest(), java.time.Duration.ofMillis(300)) {}
        val result = future.get(1, java.util.concurrent.TimeUnit.SECONDS)
        assertNotNull(result)
        assertEquals("standalone_result", result.first()?.value)
    }

    private fun unannotatedFunction(event: SimpleEventTest) {}
}

var topLevelReceived = 0

@Subscribe
fun standaloneAnnotatedFunction(event: SimpleEventTest) {
    topLevelReceived++
}

@Subscribe
fun standaloneAnnotatedFunctionWithReturn(event: SimpleEventTest): String {
    return "standalone_result"
}

class TestOrderListener {
    @Subscribe(order = 200)
    fun late(event: SimpleEventTest): String {
        return "late"
    }

    @Subscribe(order = 100)
    fun early(event: SimpleEventTest): String {
        return "early"
    }
}

class TestUnregisterListener {
    var count = 0

    @Subscribe
    fun onEvent(event: SimpleEventTest) {
        count++
    }
}
