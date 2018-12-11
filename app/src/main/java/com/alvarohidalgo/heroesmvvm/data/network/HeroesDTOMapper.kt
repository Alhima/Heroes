package com.alvarohidalgo.heroesmvvm.data.network

import com.alvarohidalgo.heroesmvvm.data.network.model.ComicsDTO
import com.alvarohidalgo.heroesmvvm.data.network.model.HeroeDTO
import com.alvarohidalgo.heroesmvvm.data.network.model.ItemDTO
import com.alvarohidalgo.heroesmvvm.domain.model.Comic
import com.alvarohidalgo.heroesmvvm.domain.model.Heroe

class HeroesDTOMapper {

    fun mapHeroes(heroesList: List<HeroeDTO>): List<Heroe> {
        return heroesList.map { mapHero(it) }
    }

    private fun mapHero(hero: HeroeDTO): Heroe = Heroe(
        hero.id,
        hero.name,
        "${hero.thumbnail.path}.${hero.thumbnail.extension}",
        hero.description,
        mapComicList(hero.comics.items)
    )

    fun mapHeroListIntoSingleHero(heroesList: List<HeroeDTO>): Heroe? {
        return heroesList.asSequence().map { mapHero(it) }.firstOrNull()
    }

    fun mapComicList(list: List<ItemDTO>): List<Comic> {
        return list.map { Comic(it.name, it.resourceURI) }
    }
}