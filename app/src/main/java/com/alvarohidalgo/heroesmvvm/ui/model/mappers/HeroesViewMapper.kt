package com.alvarohidalgo.heroesmvvm.ui.model.mappers

import com.alvarohidalgo.heroesmvvm.domain.model.Comic
import com.alvarohidalgo.heroesmvvm.domain.model.Heroe
import com.alvarohidalgo.heroesmvvm.ui.model.ComicVM
import com.alvarohidalgo.heroesmvvm.ui.model.HeroDetailItem
import com.alvarohidalgo.heroesmvvm.ui.model.HeroeVM

class HeroesViewMapper {

    fun mapHero(hero: Heroe): HeroeVM {
        return HeroeVM(hero.id, hero.name, hero.photoUrl, addDetailsToList(hero))
    }

    private fun addDetailsToList(hero: Heroe): List<HeroDetailItem> {
        return listOf<HeroDetailItem>(
            HeroDetailItem.Description(hero.description),
            HeroDetailItem.ComicList(mapComicList(hero.comics))
        )
    }

    private fun mapComicList(comics: List<Comic>): List<ComicVM> {
        return comics.map { ComicVM(it.name, it.url) }
    }

    fun mapHeroes(heroList: List<Heroe>): List<HeroeVM> {
        return heroList.map { mapHero(it) }
    }
}
