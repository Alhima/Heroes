package com.alvarohidalgo.heroesmvvm.ui.herodetail

import com.alvarohidalgo.heroesmvvm.ui.base.arch.Action
import com.alvarohidalgo.heroesmvvm.ui.base.arch.Route
import com.alvarohidalgo.heroesmvvm.ui.base.arch.State


sealed class HeroDetailState : State()

sealed class HeroDetailRoute : Route()

sealed class HeroDetailAction : Action()