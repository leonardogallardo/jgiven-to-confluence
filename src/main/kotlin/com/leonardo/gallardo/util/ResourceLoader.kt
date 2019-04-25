package com.leonardo.gallardo.util

import java.nio.charset.Charset
import java.util.*

class ResourceLoader {

    val cache = HashMap<String, String>()

    fun loadAsString(fileName: String): String {

        var content = cache.get(fileName)
        if (content == null) {
            content = this::class.java.getResource("/$fileName").readText(Charset.defaultCharset())
            cache.put(fileName, content)
        }

        return content
    }

    fun loadProperties(fileName: String): Properties {
        val stream = this::class.java.getResourceAsStream("/$fileName")
        val prop = Properties()
        prop.load(stream)
        return prop
    }
}