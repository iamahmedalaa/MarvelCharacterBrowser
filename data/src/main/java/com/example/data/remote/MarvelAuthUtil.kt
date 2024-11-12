package com.example.data.remote

import java.security.MessageDigest

object MarvelAuthUtil {
    fun generateAuthParams(timestamp: String): Pair<String, String> {
        val hash = "$timestamp${BuildConfig.MARVEL_PRIVATE_KEY}${BuildConfig.MARVEL_PUBLIC_KEY}".md5()
        return Pair(BuildConfig.MARVEL_PUBLIC_KEY, hash)
    }

    private fun String.md5(): String {
        return MessageDigest.getInstance("MD5").digest(toByteArray())
            .joinToString("") { "%02x".format(it) }
    }
}
