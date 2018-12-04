package com.alvarohidalgo.heroesmvvm.ui.main

import com.alvarohidalgo.heroesmvvm.domain.usecase.GetPrincipalHeroesUC
import com.alvarohidalgo.heroesmvvm.domain.usecase.SearchHeroesByTextUC
import com.alvarohidalgo.heroesmvvm.ui.base.arch.BaseViewModel
import com.alvarohidalgo.heroesmvvm.ui.model.HeroeVM
import com.alvarohidalgo.heroesmvvm.ui.model.mappers.HeroesViewMapper

class MainViewModel(
    private val getPrincipalHeroesUC: GetPrincipalHeroesUC,
    private val searchHeroesByTextUC: SearchHeroesByTextUC,
    private val heroesMapper: HeroesViewMapper
) : BaseViewModel<MainState, MainRoute, MainAction>() {

    fun initScreen() {
        runFirstTime {
            getAllHeroes()
        }
    }

    fun searchHeroes(name: String) {
        changeState(MainState.Loading)
        runJob {
            changeState(MainState.Data(heroesMapper.mapHeroes(searchHeroesByTextUC.execute(name)), true))
        }
    }

    fun getAllHeroes() {
        changeState(MainState.Loading)
        runJob {
            changeState(MainState.Data(heroesMapper.mapHeroes(getPrincipalHeroesUC.execute()), true))
        }
    }

    fun heroClicked(hero: HeroeVM) {
        changeRoute(MainRoute.HeroDetail(hero))
    }

}