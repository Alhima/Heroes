package com.alvarohidalgo.heroesmvvm.data.network

import com.alvarohidalgo.heroesmvvm.data.network.model.ApiResponse
import com.alvarohidalgo.heroesmvvm.data.network.model.HeroeDTO
import com.alvarohidalgo.heroesmvvm.data.network.services.HeroesApi
import com.alvarohidalgo.heroesmvvm.domain.exceptions.DataException
import com.alvarohidalgo.heroesmvvm.domain.model.Heroe
import kotlinx.coroutines.Deferred

class HeroesNetworkDataSource(private val heroesApi: HeroesApi, private val heroesMapper: HeroesDTOMapper) {

    suspend fun search(name: String, offSet: Int): List<Heroe> {
        return loadHeroes(heroesApi.getPrincipalHeroes(offSet, name))
    }

    suspend fun getAll(offSet: Int): List<Heroe> {
        return loadHeroes(heroesApi.getPrincipalHeroes(offSet))
    }

    private suspend fun loadHeroes(heroesDeferred: Deferred<ApiResponse<HeroeDTO>>): List<Heroe> {
        val deferred = heroesDeferred.await()
        val heroeList = deferred.getData()
        try {
            return heroesMapper.mapHeroes(heroeList)
        } catch (e: Exception) {
            throw DataException()
        }
    }

    private suspend fun loadSingleHeroe(heroesDeferred: Deferred<ApiResponse<HeroeDTO>>): Heroe {
        val deferred = heroesDeferred.await()
        val hero = deferred.getData()
        try {
            heroesMapper.mapHeroListIntoSingleHero(hero)?.let {
                return it
            } ?: throw DataException()
        } catch (e: Exception) {
            throw DataException()
        }
    }

    suspend fun getHeroById(id: String): Heroe {
        return loadSingleHeroe(heroesApi.getHeroeById(id))
    }
}