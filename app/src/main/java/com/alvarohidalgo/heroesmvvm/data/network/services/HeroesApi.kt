package com.alvarohidalgo.heroesmvvm.data.network.services

import com.alvarohidalgo.heroesmvvm.data.network.model.ApiResponse
import com.alvarohidalgo.heroesmvvm.data.network.model.HeroeDTO
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HeroesApi {

    @GET("/v1/public/characters")
    fun getPrincipalHeroes( @Query("offset") offset: Int, @Query("nameStartsWith") name: String? = null): Deferred<ApiResponse<HeroeDTO>>

    @GET("/v1/public/characters/{characterId}")
    fun getHeroeById(@Path("character_id") id: String): Deferred<ApiResponse<HeroeDTO>>

}