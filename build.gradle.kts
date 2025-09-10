plugins {
    kotlin("jvm") version "2.2.20"
}

group = "io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus"
version = project.property("version") as String

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