package com.alvarohidalgo.heroesmvvm.di

import com.alvarohidalgo.heroesmvvm.ui.herodetail.HeroDetailViewModel
import com.alvarohidalgo.heroesmvvm.ui.main.MainViewModel
import com.alvarohidalgo.heroesmvvm.ui.model.mappers.HeroesViewMapper
import org.koin.androidx.viewmodel.experimental.builder.viewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val presentationModule: Module = module {
    viewModel<MainViewModel>()
    viewModel<HeroDetailViewModel>()

    factory { HeroesViewMapper() }
}