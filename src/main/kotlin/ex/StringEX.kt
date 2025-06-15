@file:Suppress("unused")

package io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus.ex

import java.security.MessageDigest

fun String.toUTF8() = toByteArray(Charsets.ISO_8859_1).decodeToString()

fun String.lastPath(find: String): String {
    val index = this.indexOf(find)
    if (index < 0)
        return ""
    return this.substring(index + find.length)
}

fun String.lastPath(find: Char): String {
    val index = this.indexOf(find)
    if (index < 0)
        return ""
    return this.substring(index + 1)
}

fun String.firstPath(find: String): String {
    val index = this.indexOf(find)
    if (index < 0)
        return ""
    return this.substring(0, index)
}

fun String.firstPath(find: Char): String {
    val index = this.indexOf(find)
    if (index < 0)
        return ""
    return this.substring(0, index)
}

fun String.middlePath(first: String, last: String) = firstPath(last).lastPath(first)
fun String.middlePath(first: Char, last: Char) = firstPath(last).lastPath(first)
fun String.middlePath(first: Char, last: String) = firstPath(last).lastPath(first)
fun String.middlePath(first: String, last: Char) = firstPath(last).lastPath(first)

fun String.md5() = hashString(this, "MD5")
fun String.sha256() = hashString(this, "SHA-256")

// https://gist.github.com/lovubuntu/164b6b9021f5ba54cefc67f60f7a1a25
private fun hashString(input: String, algorithm: String): String {
    return MessageDigest
        .getInstance(algorithm)
        .digest(input.toByteArray())
        .fold(StringBuilder()) { sb, it -> sb.append("%02x".format(it)) }.toString()
}