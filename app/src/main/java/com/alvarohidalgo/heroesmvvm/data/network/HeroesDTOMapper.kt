package com.alvarohidalgo.heroesmvvm.data.network

import com.alvarohidalgo.heroesmvvm.data.network.model.HeroeDTO
import com.alvarohidalgo.heroesmvvm.domain.model.Heroe

class HeroesDTOMapper {

    fun mapHeroes(heroesList: List<HeroeDTO>): List<Heroe> {
        return heroesList.map { mapHero(it) }
    }

    fun mapHero(hero: HeroeDTO): Heroe = Heroe(hero.id, hero.name, "${hero.thumbnail.path}.${hero.thumbnail.extension}")

    fun mapHeroListIntoSingleHero(heroesList: List<HeroeDTO>): Heroe? {
        return heroesList.asSequence().map { mapHero(it) }.firstOrNull()
    }
}