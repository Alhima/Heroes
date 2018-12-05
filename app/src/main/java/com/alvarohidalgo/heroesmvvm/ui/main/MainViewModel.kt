package com.alvarohidalgo.heroesmvvm.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alvarohidalgo.heroesmvvm.domain.usecase.GetPrincipalHeroesUC
import com.alvarohidalgo.heroesmvvm.domain.usecase.SearchHeroesByTextUC
import com.alvarohidalgo.heroesmvvm.ui.base.arch.BaseViewModel
import com.alvarohidalgo.heroesmvvm.ui.model.HeroeVM
import com.alvarohidalgo.heroesmvvm.ui.model.mappers.HeroesViewMapper

class MainViewModel(
    private val getPrincipalHeroesUC: GetPrincipalHeroesUC,
    private val searchHeroesByTextUC: SearchHeroesByTextUC,
    private val heroesMapper: HeroesViewMapper
) : BaseViewModel<MainState, MainRoute, MainAction>() {

    private val listIsFull = MutableLiveData<Boolean>()
    private val listType = MutableLiveData<ListBeingLoadedType>()

    fun initScreen() {
        runFirstTime {
            getAllHeroes()
        }
    }

    fun searchHeroes(name: String) {
        changeState(MainState.Loading)
        runJob {
            changeState(MainState.Data(heroesMapper.mapHeroes(searchHeroesByTextUC.execute(name, false)), true))
        }
    }

    fun getAllHeroes() {
        changeState(MainState.Loading)
        runJob {
            changeState(MainState.Data(heroesMapper.mapHeroes(getPrincipalHeroesUC.execute(false)), true))
        }
    }

    fun heroClicked(hero: HeroeVM) {
        changeRoute(MainRoute.HeroDetail(hero))
    }

    fun isPaginatedListFull(): () -> Boolean = { listIsFull.value ?: true }
    fun isLoading(): () -> Boolean = { state.value is MainState.Loading }
    fun loadNextPage(): () -> Unit = { when(listType.value) {
        is ListBeingLoadedType.MainList -> loadMorePrincipalHeroes()
        is ListBeingLoadedType.SearchList -> loadMoreSearchedHeroes()
    } }

    private fun loadMoreSearchedHeroes() {
        changeState(MainState.Loading)
        runJob {
            changeState(MainState.Data(heroesMapper.mapHeroes(getPrincipalHeroesUC.execute(true)), true))
        }
    }

    private fun loadMorePrincipalHeroes() {
        changeState(MainState.Loading)
        runJob {
            changeState(MainState.Data(heroesMapper.mapHeroes(getPrincipalHeroesUC.execute(true)), true))
        }
    }


    sealed class ListBeingLoadedType {
        object MainList : ListBeingLoadedType()
        object SearchList : ListBeingLoadedType()
    }
}