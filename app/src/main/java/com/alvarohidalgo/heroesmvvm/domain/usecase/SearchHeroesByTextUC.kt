package com.alvarohidalgo.heroesmvvm.domain.usecase

import com.alvarohidalgo.heroesmvvm.domain.model.Heroe
import com.alvarohidalgo.heroesmvvm.domain.repositories.HeroesRepository

class SearchHeroesByTextUC(private val repo: HeroesRepository) {

    suspend fun execute(name: String, isNextPage: Boolean): List<Heroe> {
        return repo.searchHeroes(name, isNextPage)
    }
}