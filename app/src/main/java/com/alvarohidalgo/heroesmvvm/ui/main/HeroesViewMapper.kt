package com.alvarohidalgo.heroesmvvm.ui.main

import com.alvarohidalgo.heroesmvvm.domain.model.Heroe
import com.alvarohidalgo.heroesmvvm.ui.main.model.HeroeVM

class HeroesViewMapper {

    fun mapHero(hero: Heroe): HeroeVM {
        return HeroeVM(hero.name)
    }

    fun mapHeroes(heroList: List<Heroe>): List<HeroeVM> {
        return heroList.map { mapHero(it) }
    }
}
