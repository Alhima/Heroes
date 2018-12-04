package com.alvarohidalgo.heroesmvvm.di

import com.alvarohidalgo.heroesmvvm.data.repository.HeroesRepositoryImpl
import com.alvarohidalgo.heroesmvvm.domain.repositories.HeroesRepository
import com.alvarohidalgo.heroesmvvm.domain.usecase.GetHeroByIdUC
import com.alvarohidalgo.heroesmvvm.domain.usecase.GetPrincipalHeroesUC
import com.alvarohidalgo.heroesmvvm.domain.usecase.SearchHeroesByTextUC
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val domainModule: Module = module {

    factory { GetPrincipalHeroesUC(get()) }
    factory { SearchHeroesByTextUC(get()) }
    factory { GetHeroByIdUC(get()) }



    factory<HeroesRepository> { HeroesRepositoryImpl(get(), get()) }
}