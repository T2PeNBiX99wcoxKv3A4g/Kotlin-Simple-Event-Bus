import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    `maven-publish`
}

group = providers.gradleProperty("group").get()
version = providers.gradleProperty("version").get()

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${property("kotlinx-coroutines.version")}")
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(providers.gradleProperty("jdk_version").get().toInt())
    compilerOptions {
        jvmTarget.set(JvmTarget.fromTarget(providers.gradleProperty("jvm_target").get()))
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(providers.gradleProperty("jvm_target").map { it.toInt() })
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }

    repositories {
        maven {
            name = "GitHubPages"
            url = layout.buildDirectory.dir("repo").get().asFile.toURI()
        }
    }
}