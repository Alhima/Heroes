package com.alvarohidalgo.heroesmvvm.di

import com.alvarohidalgo.heroesmvvm.data.network.ApiConfig
import com.alvarohidalgo.heroesmvvm.data.network.TokenInterceptor
import com.alvarohidalgo.heroesmvvm.data.network.services.HeroesApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    factory { TokenInterceptor() }
    factory { HttpLoggingInterceptor() }

    factory<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(get<TokenInterceptor>())
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    factory<Retrofit> {
        Retrofit.Builder().addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .baseUrl(ApiConfig.API_URL).build()
    }

    factory<HeroesApi> { get<Retrofit>().create(HeroesApi::class.java) }
}