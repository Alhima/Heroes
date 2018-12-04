package com.alvarohidalgo.heroesmvvm.domain.usecase

import com.alvarohidalgo.heroesmvvm.domain.model.Heroe
import com.alvarohidalgo.heroesmvvm.domain.repositories.HeroesRepository

class GetHeroByIdUC(val repo: HeroesRepository) {
    suspend fun execute(id: String): Heroe = repo.getHeroById(id)
}