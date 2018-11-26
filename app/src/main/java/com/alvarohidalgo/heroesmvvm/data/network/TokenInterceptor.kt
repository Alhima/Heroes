package com.alvarohidalgo.heroesmvvm.data.network

import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(chain.request().newBuilder().addHeader(ApiConfig.API_KEY_PARAM, ApiConfig.API_TOKEN).build())
}