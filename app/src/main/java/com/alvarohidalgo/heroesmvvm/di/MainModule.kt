package com.alvarohidalgo.heroesmvvm.di

import com.alvarohidalgo.heroesmvvm.data.network.HeroesNetworkDataSource
import com.alvarohidalgo.heroesmvvm.data.persistence.HeroesLocalDataSource
import com.alvarohidalgo.heroesmvvm.domain.usecase.GetPrincipalHeroesUC
import com.alvarohidalgo.heroesmvvm.domain.usecase.SearchHeroesByTextUC
import com.alvarohidalgo.heroesmvvm.data.repository.HeroesRepository
import com.alvarohidalgo.heroesmvvm.ui.main.MainViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val mainModule: Module = module {
    viewModel { MainViewModel(get(), get()) }

    single { HeroesRepository(get(), get()) }
    single { GetPrincipalHeroesUC(get()) }
    single { SearchHeroesByTextUC(get()) }
    single { HeroesLocalDataSource() }
    single { HeroesNetworkDataSource() }
}