package com.alvarohidalgo.heroesmvvm.ui.herodetail

import android.os.Bundle
import android.view.MenuItem
import com.alvarohidalgo.heroesmvvm.R
import com.alvarohidalgo.heroesmvvm.navigation.Navigation
import com.alvarohidalgo.heroesmvvm.ui.base.BaseActivity
import com.alvarohidalgo.heroesmvvm.ui.base.arch.ViewModelOwner
import com.alvarohidalgo.heroesmvvm.ui.model.HeroeVM
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_herodetail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HeroDetailActivity : BaseActivity(), ViewModelOwner<HeroDetailState, HeroDetailRoute, HeroDetailAction> {

    val viewModel: HeroDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_herodetail)
        val heroId = intent?.extras?.getString(Navigation.HeroDetail.BUNDLE_HERO_ID).orEmpty()
        val heroPhotoUrl = intent?.extras?.getString(Navigation.HeroDetail.BUNDLE_HERO_URL).orEmpty()
        val heroName = intent?.extras?.getString(Navigation.HeroDetail.BUNDLE_HERO_NAME).orEmpty()
        photoImageView.transitionName = heroPhotoUrl
        supportActionBar?.customView?.transitionName = heroName

        Glide.with(this).load(heroPhotoUrl).into(photoImageView)
        setSupportActionBar(toolbar)
        viewModel.observe(this)
        viewModel.initScreen(heroId)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = heroName
    }

    override fun onState(s: HeroDetailState?) {
        s?.let {
            when (it) {
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setLoading() {

    }

    private fun loadHeroData(hero: HeroeVM) {

    }
}