package io.github.t2PeNBiX99wcoxKv3A4g.kotlinSimpleEventBus

import java.io.File
import java.net.URL

object Utils {
    fun resourceUrl(name: String): URL? = this.javaClass.getResource(name)

    fun resourceFile(name: String): File? {
        val url = resourceUrl(name) ?: return null
        return File(url.file)
    }
}