package com.alvarohidalgo.heroesmvvm.ui.herodetail

import android.os.Bundle
import com.alvarohidalgo.heroesmvvm.R
import com.alvarohidalgo.heroesmvvm.navigation.Navigation
import com.alvarohidalgo.heroesmvvm.ui.base.BaseActivity
import com.alvarohidalgo.heroesmvvm.ui.base.arch.ViewModelOwner
import com.alvarohidalgo.heroesmvvm.ui.model.HeroeVM
import org.koin.androidx.viewmodel.ext.android.viewModel

class HeroDetailActivity : BaseActivity(), ViewModelOwner<HeroDetailState, HeroDetailRoute, HeroDetailAction> {

    val viewModel: HeroDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_herodetail)
        val heroId = intent?.extras?.getString(Navigation.HeroDetail.BUNDLE_HERO_ID).orEmpty()
        viewModel.observe(this)
        viewModel.initScreen(heroId)
    }

    override fun onState(s: HeroDetailState?) {
        s?.let{
            when(it) {
                is HeroDetailState.Data -> loadHeroData(it.hero)
                is HeroDetailState.Loading -> setLoading()
            }
        }
    }

    override fun onRoute(r: HeroDetailRoute?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAction(r: HeroDetailAction?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private fun setLoading() {

    }

    private fun loadHeroData(hero: HeroeVM) {
        val heroe = hero.name
    }
}