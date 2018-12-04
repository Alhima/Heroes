package com.alvarohidalgo.heroesmvvm.di

import com.alvarohidalgo.heroesmvvm.data.network.HeroesDTOMapper
import com.alvarohidalgo.heroesmvvm.data.network.HeroesNetworkDataSource
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val networkDataModule: Module = module {

    factory { HeroesNetworkDataSource(get(), get()) }
    factory { HeroesDTOMapper() }
}