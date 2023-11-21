package com.riti.data.util

import org.apache.commons.codec.digest.HmacUtils

/**
 * singleton
 */
object Util {
    @JvmStatic
    fun randomAlphabetNumber(wishLength:Int): String{

        val charPool: List<Char> = ('a'..'z')+('A'..'Z')+('0'..'9')

        return (0..wishLength)
                .map { kotlin.random.Random.nextInt(0,charPool.size) }
                .map(charPool::get)
                .joinToString("")
    }
    @JvmStatic
    fun encryptUserPassword(password: String, hmacKey: String): String {
        return HmacUtils("HmacMD5", hmacKey).hmacHex(password)
    }

}