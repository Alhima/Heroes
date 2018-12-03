package com.alvarohidalgo.heroesmvvm.data.network

import com.alvarohidalgo.heroesmvvm.data.network.model.ApiResponse
import com.alvarohidalgo.heroesmvvm.data.network.model.HeroeDTO
import com.alvarohidalgo.heroesmvvm.data.network.services.HeroesApi
import com.alvarohidalgo.heroesmvvm.domain.exceptions.DataException
import com.alvarohidalgo.heroesmvvm.domain.model.Heroe
import kotlinx.coroutines.Deferred

class HeroesNetworkDataSource(private val heroesApi: HeroesApi, private val heroesMapper: HeroesDTOMapper) {

    suspend fun search(name: String): List<Heroe> {
        return loadHeroes(heroesApi.getPrincipalHeroes(name))
    }

    suspend fun getAll(): List<Heroe> {
        return loadHeroes(heroesApi.getPrincipalHeroes())
    }

    private suspend fun loadHeroes(heroesDeferred: Deferred<ApiResponse<HeroeDTO>>): List<Heroe> {
        val heroeList = heroesDeferred.await().getData()
        try {
            return heroesMapper.mapHeroes(heroeList)
        } catch (e: Exception) {
            throw DataException()
        }
    }
}