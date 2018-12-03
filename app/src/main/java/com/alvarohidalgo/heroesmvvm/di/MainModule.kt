package com.alvarohidalgo.heroesmvvm.di

import com.alvarohidalgo.heroesmvvm.data.network.HeroesDTOMapper
import com.alvarohidalgo.heroesmvvm.data.network.HeroesNetworkDataSource
import com.alvarohidalgo.heroesmvvm.data.persistence.HeroesLocalDataSource
import com.alvarohidalgo.heroesmvvm.data.repository.HeroesRepositoryImpl
import com.alvarohidalgo.heroesmvvm.domain.repositories.HeroesRepository
import com.alvarohidalgo.heroesmvvm.domain.usecase.GetPrincipalHeroesUC
import com.alvarohidalgo.heroesmvvm.domain.usecase.SearchHeroesByTextUC
import com.alvarohidalgo.heroesmvvm.ui.main.MainViewModel
import com.alvarohidalgo.heroesmvvm.ui.model.mappers.HeroesViewMapper
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val mainModule: Module = module {
    viewModel { MainViewModel(get(),get(),get())}

    factory { HeroesViewMapper() }
    factory { GetPrincipalHeroesUC(get()) }
    factory { SearchHeroesByTextUC(get()) }

    factory<HeroesRepository> { HeroesRepositoryImpl(get(), get()) }

    factory { HeroesDTOMapper() }
    factory { HeroesLocalDataSource() }
    factory { HeroesNetworkDataSource(get(), get()) }
}