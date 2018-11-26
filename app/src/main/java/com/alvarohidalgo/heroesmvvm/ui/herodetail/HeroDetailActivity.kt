package com.alvarohidalgo.heroesmvvm.ui.herodetail

import android.os.Bundle
import com.alvarohidalgo.heroesmvvm.R
import com.alvarohidalgo.heroesmvvm.ui.base.BaseActivity
import com.alvarohidalgo.heroesmvvm.ui.base.arch.ViewModelOwner

class HeroDetailActivity : BaseActivity(), ViewModelOwner<HeroDetailState, HeroDetailRoute, HeroDetailAction> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_herodetail)
    }

    override fun onState(s: HeroDetailState?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRoute(r: HeroDetailRoute?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAction(r: HeroDetailAction?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}