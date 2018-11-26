package com.alvarohidalgo.heroesmvvm.ui.main

import android.os.Bundle
import com.alvarohidalgo.heroesmvvm.R
import com.alvarohidalgo.heroesmvvm.ui.base.BaseActivity
import com.alvarohidalgo.heroesmvvm.ui.base.arch.ViewModelOwner

class MainActivity : BaseActivity(), ViewModelOwner<MainState, MainRoute, MainAction> {


    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)

    }

    override fun onState(s: MainState?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRoute(r: MainRoute?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAction(r: MainAction?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}