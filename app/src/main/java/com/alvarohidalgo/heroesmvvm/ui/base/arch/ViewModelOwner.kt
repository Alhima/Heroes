package com.alvarohidalgo.heroesmvvm.ui.base.arch

interface ViewModelOwner<S: State, R: Route, A: Action> {
    fun onState(s: S?)
    fun onRoute(r: R?)
    fun onAction(r: A?)
}