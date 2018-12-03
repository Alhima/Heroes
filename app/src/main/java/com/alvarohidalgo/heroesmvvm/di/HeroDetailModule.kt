package com.alvarohidalgo.heroesmvvm.di

import com.alvarohidalgo.heroesmvvm.ui.herodetail.HeroDetailViewModel
import org.koin.androidx.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val heroDetailModule : Module = module {
    viewModel<HeroDetailViewModel>()
    single {}

}