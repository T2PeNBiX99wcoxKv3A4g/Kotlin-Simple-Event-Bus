@file:Suppress("unused")

package io.github.ykysnk.kotlinSimpleEventBus.extensions

// https://stackoverflow.com/questions/39806721/i-want-to-detect-if-a-jvm-class-is-a-kotlin-class-or-not
fun Class<*>.isKotlinClass(): Boolean = declaredAnnotations.any { it.annotationClass == Metadata::class }