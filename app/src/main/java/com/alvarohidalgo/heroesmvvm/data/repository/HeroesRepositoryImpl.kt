package com.alvarohidalgo.heroesmvvm.data.repository

import com.alvarohidalgo.heroesmvvm.data.network.HeroesNetworkDataSource
import com.alvarohidalgo.heroesmvvm.data.persistence.HeroesLocalDataSource
import com.alvarohidalgo.heroesmvvm.domain.model.Heroe
import com.alvarohidalgo.heroesmvvm.domain.repositories.HeroesRepository

class HeroesRepositoryImpl(
    val networkDataSource: HeroesNetworkDataSource,
    val localDataSource: HeroesLocalDataSource
) : HeroesRepository {

    suspend fun searchHeroes(name: String): List<Heroe> {
        return networkDataSource.search(name)
    }

    suspend fun getAllHeroes(): List<Heroe> {
        return networkDataSource.getAll()
    }

}