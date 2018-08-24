package com.marcskow.url.shortener.url

import org.springframework.stereotype.Component

// Basing on https://github.com/delight-im/ShortURL/blob/master/Java/ShortURL.java
// Converted to Kotlin with only minor modifications
@Component
class IdEncoder {
    companion object {
        const val ALPHABET = "23456789bcdfghjkmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ-_"
        const val BASE = ALPHABET.length
    }

    fun encode(id: Long): String {
        var number = id
        var str = ""
        while (id > 0) {
            str += ALPHABET[(number % BASE).toInt()]
            number /= BASE
        }
        return str
    }

    fun decode(str: String): Long {
        var num = 0L
        for (i in 0 until str.length) {
            num = num * BASE + ALPHABET.indexOf(str[i])
        }
        return num
    }
}