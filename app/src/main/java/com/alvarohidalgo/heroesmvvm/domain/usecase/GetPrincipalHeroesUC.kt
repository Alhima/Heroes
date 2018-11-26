package com.alvarohidalgo.heroesmvvm.domain.usecase

import com.alvarohidalgo.heroesmvvm.domain.model.Heroe
import com.alvarohidalgo.heroesmvvm.domain.repositories.HeroesRepository

class GetPrincipalHeroesUC(private val repo: HeroesRepository) {

    suspend fun execute(): List<Heroe> {
        return repo.getAllHeroes()
    }

}