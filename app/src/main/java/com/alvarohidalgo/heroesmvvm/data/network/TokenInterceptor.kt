package com.alvarohidalgo.heroesmvvm.data.network

import okhttp3.Interceptor
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val ts = Date().time
        val hash = generateMd5(ts, ApiConfig.API_TOKEN_PRIVATE, ApiConfig.API_TOKEN)
        val newUrl = original.url().newBuilder().addQueryParameter(ApiConfig.API_KEY_PARAM, ApiConfig.API_TOKEN)
            .addQueryParameter(ApiConfig.TS_PARAM, ts.toString())
            .addQueryParameter(ApiConfig.HASH_PARAM, hash)
            .build()
        val requestBuilder = original.newBuilder().url(newUrl)
        return chain.proceed(requestBuilder.build())
    }

    fun generateMd5(timestamp: Long, privateKey: String, publicKey: String): String? {
        return try {
            val concatResult = timestamp.toString() + privateKey + publicKey
            md5(concatResult)
        } catch (e: Exception) {
            null
        }

    }

    @Throws(NoSuchAlgorithmException::class)
    fun md5(s: String): String {
        // Create MD5 Hash
        val digest = MessageDigest.getInstance("MD5")
        digest.update(s.toByteArray())
        val messageDigest = digest.digest()
        val bigInt = BigInteger(1, messageDigest)
        var hashText = bigInt.toString(16)
        // Now we need to zero pad it if you actually want the full 32
        // chars.
        while (hashText.length < 32) {
            hashText = "0$hashText"
        }
        return hashText
    }

}