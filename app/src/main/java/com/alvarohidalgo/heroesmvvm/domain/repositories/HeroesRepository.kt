package com.alvarohidalgo.heroesmvvm.domain.repositories

import com.alvarohidalgo.heroesmvvm.domain.model.Heroe

interface HeroesRepository {
    suspend fun searchHeroes(name: String): List<Heroe>
    suspend fun getAllHeroes(): List<Heroe>
}