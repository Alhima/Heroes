package com.alvarohidalgo.heroesmvvm.ui.herodetail

import com.alvarohidalgo.heroesmvvm.domain.usecase.GetHeroByIdUC
import com.alvarohidalgo.heroesmvvm.ui.base.arch.BaseViewModel
import com.alvarohidalgo.heroesmvvm.ui.model.mappers.HeroesViewMapper


class HeroDetailViewModel(val getHeroUC: GetHeroByIdUC, val heroMapper: HeroesViewMapper) :
    BaseViewModel<HeroDetailState, HeroDetailRoute, HeroDetailAction>() {

    fun initScreen(heroId: String) {
        runFirstTime {
            getHeroData(heroId)
        }
    }

    private fun getHeroData(id: String) {
        changeState(HeroDetailState.Loading)
        runJob {
            changeState(HeroDetailState.Data(heroMapper.mapHero(getHeroUC.execute(id))))
        }
    }
}