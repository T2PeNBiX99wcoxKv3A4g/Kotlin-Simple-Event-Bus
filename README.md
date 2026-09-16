# Kotlin Simple Event Bus

Simple event bus by using `SharedFlow`

`build.gradle.kts`

```kotlin
repositories {
    maven("https://t2penbix99wcoxkv3a4g.github.io/Kotlin-Simple-Event-Bus/") {
        name = "Kotlin Simple Event Bus"
    }
}

dependencies {
    implementation("io.github.ykysnk:kotlin-simple-event-bus:0.4.8")
}
```

`build.gradle`

```groovy
repositories {
    mavenCentral()
    maven {
        url = 'https://t2penbix99wcoxkv3a4g.github.io/Kotlin-Simple-Event-Bus/'
        name = 'Kotlin Simple Event Bus'
    }
}

dependencies {
    implementation 'io.github.ykysnk:kotlin-simple-event-bus:0.4.8'
}
```

## Sample

```kotlin
val eventBus = EventBus {
    // Handle error
}

val eventBusWithTimeoutChange = EventBus(1.seconds) {
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
        val ret = eventBus.publish<Boolean>(SimpleEvent3(), 400.milliseconds) {
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