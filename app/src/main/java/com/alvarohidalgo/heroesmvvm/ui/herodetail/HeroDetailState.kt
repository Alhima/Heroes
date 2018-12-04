package com.alvarohidalgo.heroesmvvm.ui.herodetail

import com.alvarohidalgo.heroesmvvm.ui.base.arch.Action
import com.alvarohidalgo.heroesmvvm.ui.base.arch.Route
import com.alvarohidalgo.heroesmvvm.ui.base.arch.State
import com.alvarohidalgo.heroesmvvm.ui.model.HeroeVM


sealed class HeroDetailState : State() {
    data class Data(val hero: HeroeVM) : HeroDetailState()
    object Loading : HeroDetailState()
}

sealed class HeroDetailRoute : Route()

sealed class HeroDetailAction : Action()