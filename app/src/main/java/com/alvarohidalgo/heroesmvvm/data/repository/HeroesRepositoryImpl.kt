package com.alvarohidalgo.heroesmvvm.data.repository

import com.alvarohidalgo.heroesmvvm.data.network.HeroesNetworkDataSource
import com.alvarohidalgo.heroesmvvm.data.persistence.HeroesLocalDataSource
import com.alvarohidalgo.heroesmvvm.domain.model.Heroe
import com.alvarohidalgo.heroesmvvm.domain.repositories.HeroesRepository

class HeroesRepositoryImpl(
    val networkDataSource: HeroesNetworkDataSource,
    val localDataSource: HeroesLocalDataSource
) : HeroesRepository {

    private val itemsPaginatedNumber = 20
    private var page = 0

    override suspend fun searchHeroes(name: String, isNextPage: Boolean): List<Heroe> {
        return networkDataSource.search(name, getOffset(isNextPage))
    }

    override suspend fun getAllHeroes(isNextPage: Boolean): List<Heroe> {
        return networkDataSource.getAll(getOffset(isNextPage))
    }

    override suspend fun getHeroById(id: String): Heroe {
        return networkDataSource.getHeroById(id)
    }


    private fun getOffset(nextPage: Boolean): Int {
        if (nextPage) page += 1 else page = 0
        return page * itemsPaginatedNumber
    }

}