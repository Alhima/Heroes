package com.alvarohidalgo.heroesmvvm.domain.repositories

import com.alvarohidalgo.heroesmvvm.domain.model.Heroe

interface HeroesRepository {
    suspend fun searchHeroes(name: String, isNextPage: Boolean): List<Heroe>
    suspend fun getAllHeroes(isNextPage: Boolean): List<Heroe>
    suspend fun getHeroById(id: String) : Heroe
}