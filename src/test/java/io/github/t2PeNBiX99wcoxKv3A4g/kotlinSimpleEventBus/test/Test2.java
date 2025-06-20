package io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.test;

import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.event.SimpleEventTest;
import io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.eventBus.Subscribe;

import java.lang.reflect.Method;

public class Test2 {
    public Test2() {
        // So sad for java
//        TestKt.getEventBus().subscribe2(Test2::TestEvent2);
    }

    private void Test(Method testEvent2) {
    }

    @Subscribe
    private void TestEvent2(SimpleEventTest event) {

    }
    
    private boolean Test(){
        return false;
    }
}
