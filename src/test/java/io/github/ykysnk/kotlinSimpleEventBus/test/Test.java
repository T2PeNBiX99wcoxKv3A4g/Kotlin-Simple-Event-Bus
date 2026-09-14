package io.github.ykysnk.kotlinSimpleEventBus.test;

import io.github.ykysnk.kotlinSimpleEventBus.event.SimpleEventCancel;
import io.github.ykysnk.kotlinSimpleEventBus.event.SimpleEventTest;
import io.github.ykysnk.kotlinSimpleEventBus.event.SimpleTick;
import io.github.ykysnk.kotlinSimpleEventBus.eventBus.EventBus;
import io.github.ykysnk.kotlinSimpleEventBus.eventBus.Subscribe;

import java.time.Duration;

public class Test {
    @SuppressWarnings("unused")
    private static final EventBus eventBus = EventBus.createFromJava(Duration.ofSeconds(3), (throwable) -> {
    });

    public Test() {
        TestKt.getEventBus().register(this);
        TestKt.getEventBus().publish(new SimpleEventTest());
        var ret = TestKt.getEventBus().<Boolean>publishUnSafe(new SimpleEventCancel(), Duration.ofMillis(500L), (throwable) -> {
        });
    }

    @Subscribe
    private void Tick(SimpleTick event) {
//        System.out.println("event tick in java: " + event + " " + this);
    }

    @Subscribe
    private void TestEvent(SimpleEventTest event) {
        System.out.println("event in java: " + event + " " + this);
    }

    @Subscribe
    private static void TestEventStatic(SimpleEventTest event) {
        System.out.println("event in java static: " + event);
    }
}
