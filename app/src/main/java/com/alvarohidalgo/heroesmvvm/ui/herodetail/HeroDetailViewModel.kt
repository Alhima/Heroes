package com.alvarohidalgo.heroesmvvm.ui.herodetail

import com.alvarohidalgo.heroesmvvm.domain.usecase.GetHeroByIdUC
import com.alvarohidalgo.heroesmvvm.ui.base.arch.BaseViewModel
import com.alvarohidalgo.heroesmvvm.ui.model.HeroeVM
import com.alvarohidalgo.heroesmvvm.ui.model.mappers.HeroesViewMapper


class HeroDetailViewModel(val getHeroUC: GetHeroByIdUC, val heroMapper: HeroesViewMapper) :
    BaseViewModel<HeroDetailState, HeroDetailRoute, HeroDetailAction>() {

    fun initScreen(heroId: String) {
        runFirstTime {
            getHeroData(heroId)
        }
    }

    private fun getHeroData(id: String) {
        runJob {
            changeState(HeroDetailState.Loading)
            val hero: HeroeVM = heroMapper.mapHero(getHeroUC.execute(id))
            changeState(HeroDetailState.Data(hero))
        }

    }
}