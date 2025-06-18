package io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.test;

import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.event.SimpleEventTest;
import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.eventBus.EventBus;
import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.eventBus.Subscribe;

public class Test {
    private static final EventBus eventBus = new EventBus(3000L, (throwable) -> {
    });

    public Test() {
        eventBus.register(this);
    }
    
    private void Tick(){
        eventBus.publish(new SimpleEventTest());
    }

    @Subscribe(event = "Simple")
    private void TestEvent() {
        
    }
}
