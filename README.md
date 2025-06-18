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
    implementation("com.github.t2PeNBiX99wcoxKv3A4g:kotlin-simple-event-bus:0.2.0")
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
    implementation 'com.github.t2PeNBiX99wcoxKv3A4g:kotlin-simple-event-bus:0.2.0'
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

class SimpleEvent : Event() {
    override fun isCancellable(): Boolean = true
}

class SimpleEvent2 : Event() {
    override fun isCancellable(): Boolean = true
}

class SamplePush {
    fun tick() {
        eventBus.publish(SimpleEvent())
        eventBusWithTimeoutChange.publish(SimpleEvent2())
    }
}

class SampleHandle {
    init {
        eventBus.register(this)
        eventBusWithTimeoutChange.register(this)
    }

    @Subscribe("SimpleEvent")
    fun onEventTrigger() {
        // Do something
    }

    @Subscribe("SimpleEvent2")
    fun onEventTrigger2() {
        // Do something
    }
}
```