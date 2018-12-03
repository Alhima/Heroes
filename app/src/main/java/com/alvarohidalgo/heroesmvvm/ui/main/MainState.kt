package com.alvarohidalgo.heroesmvvm.ui.main

import com.alvarohidalgo.heroesmvvm.ui.base.arch.Action
import com.alvarohidalgo.heroesmvvm.ui.base.arch.Route
import com.alvarohidalgo.heroesmvvm.ui.base.arch.State
import com.alvarohidalgo.heroesmvvm.ui.herodetail.HeroDetailRoute
import com.alvarohidalgo.heroesmvvm.ui.model.HeroeVM

sealed class MainState : State() {
    object Loading : MainState()
    data class Data(val heroes: List<HeroeVM>, val cleanStack: Boolean) : MainState()
}

sealed class MainRoute : Route() {
    data class HeroDetail(val id: String) : MainRoute()
}

sealed class MainAction : Action()