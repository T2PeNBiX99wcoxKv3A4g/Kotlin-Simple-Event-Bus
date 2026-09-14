pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }

    plugins {
        kotlin("jvm") version providers.gradleProperty("jvm_version")
        kotlin("plugin.serialization") version providers.gradleProperty("jvm_version")
    }
}

rootProject.name = "kotlin-simple-event-bus"