plugins {
    kotlin("jvm") version "2.1.20"
}

group = "io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${property("kotlinx-coroutines.version")}")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(22)
}