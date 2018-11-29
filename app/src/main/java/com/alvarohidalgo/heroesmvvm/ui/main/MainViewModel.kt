package com.alvarohidalgo.heroesmvvm.ui.main

import com.alvarohidalgo.heroesmvvm.domain.usecase.GetPrincipalHeroesUC
import com.alvarohidalgo.heroesmvvm.domain.usecase.SearchHeroesByTextUC
import com.alvarohidalgo.heroesmvvm.ui.base.arch.BaseViewModel
import com.alvarohidalgo.heroesmvvm.ui.model.mappers.HeroesViewMapper

class MainViewModel(
    private val getPrincipalHeroesUC: GetPrincipalHeroesUC,
    private val searchHeroesByTextUC: SearchHeroesByTextUC,
    private val heroesMapper: HeroesViewMapper
) : BaseViewModel<MainState, MainRoute, MainAction>() {


    fun searchHeroes(name: String) {
        changeState(MainState.Loading)
        runJob {
            changeState(MainState.Data(heroesMapper.mapHeroes(searchHeroesByTextUC.execute(name))))
        }
    }

    fun getAllHeroes() {

        changeState(MainState.Loading)
        runJob {
            changeState(MainState.Data(heroesMapper.mapHeroes(getPrincipalHeroesUC.execute())))
        }
    }

}