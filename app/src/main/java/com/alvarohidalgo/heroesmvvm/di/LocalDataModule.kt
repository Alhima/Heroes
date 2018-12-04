package com.alvarohidalgo.heroesmvvm.di

import com.alvarohidalgo.heroesmvvm.data.persistence.HeroesLocalDataSource
import org.koin.dsl.module.module

val localDataModule = module {

    factory { HeroesLocalDataSource() }
}