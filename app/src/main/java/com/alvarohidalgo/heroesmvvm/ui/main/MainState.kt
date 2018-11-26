package com.alvarohidalgo.heroesmvvm.ui.main

import com.alvarohidalgo.heroesmvvm.ui.base.arch.Action
import com.alvarohidalgo.heroesmvvm.ui.base.arch.Route
import com.alvarohidalgo.heroesmvvm.ui.base.arch.State
import com.alvarohidalgo.heroesmvvm.ui.model.HeroeVM

sealed class MainState : State() {
    object Loading : MainState()
    data class Data(val heroes: List<HeroeVM>) : MainState()
}

sealed class MainRoute : Route()

sealed class MainAction : Action()