package com.alvarohidalgo.heroesmvvm

import android.app.Application
import com.alvarohidalgo.heroesmvvm.di.*
import org.koin.android.ext.android.startKoin

class HeroApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(
            this, listOf(
                networkModule,
                presentationModule,
                domainModule,
                networkDataModule,
                localDataModule
            )
        )
    }
}