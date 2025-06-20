# Kotlin Simple Event Bus

Simple event bus by using `SharedFlow`

`build.gradle.kts`
```kotlin
repositories {
    mavenCentral()
    maven {
        url = uri("https://jitpack.io")
    }
}

dependencies {
    implementation("com.github.t2PeNBiX99wcoxKv3A4g:kotlin-simple-event-bus:0.3.2")
}
```

`build.gradle`
```groovy
repositories {
    mavenCentral()
    maven {
        url = 'https://jitpack.io'
    }
}

dependencies {
    implementation 'com.github.t2PeNBiX99wcoxKv3A4g:kotlin-simple-event-bus:0.3.2'
}
```

## Sample

```kotlin
val eventBus = EventBus {
    // Handle error
}

val eventBusWithTimeoutChange = EventBus(1000L) {
    // Handle error
}

class SimpleEvent : Event()

class SimpleEvent2 : Event()

class SimpleEvent3 : Event()

class SamplePush {
    fun tick() {
        eventBus.publish(SimpleEvent())
        eventBusWithTimeoutChange.publish(SimpleEvent2())
    }

    fun someEvent() {
        val ret = eventBus.publish<Boolean>(SimpleEvent3(), 400L) {
            println("Error: $it")
        }

        ret.forEach {
            println("entry: $it")
        }

        println("return size ${ret.size}")
    }
}

class SampleHandle {
    init {
        eventBus.register(this)
        eventBusWithTimeoutChange.register(this)
    }

    @Subscribe
    fun onEventTrigger(event: SimpleEvent) {
        // Do something
    }

    @Subscribe
    fun onEventTrigger2(event: SimpleEvent2) {
        // Do something
    }

    @Subscribe
    fun onEventTrigger3(event: SimpleEvent3) {
        // Do something
    }
}
```