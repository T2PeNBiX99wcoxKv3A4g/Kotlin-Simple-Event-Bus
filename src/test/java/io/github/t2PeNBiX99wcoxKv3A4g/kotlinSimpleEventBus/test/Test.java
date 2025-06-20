package io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.test;

import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.event.SimpleEventTest;
import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.event.SimpleTick;
import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.eventBus.EventBus;
import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.eventBus.Subscribe;

public class Test {
    @SuppressWarnings("unused")
    private static final EventBus eventBus = new EventBus(3000L, (throwable) -> {
    });

    public Test() {
        TestKt.getEventBus().register(this);
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
