package com.alvarohidalgo.heroesmvvm.ui.main

import android.os.Bundle
import android.view.Menu
import com.alvarohidalgo.heroesmvvm.R
import com.alvarohidalgo.heroesmvvm.ui.base.BaseActivity
import com.alvarohidalgo.heroesmvvm.ui.base.arch.ViewModelOwner
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.app.SearchManager
import android.content.Context
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import com.alvarohidalgo.heroesmvvm.ui.base.extensions.debounce
import com.alvarohidalgo.heroesmvvm.ui.base.extensions.onTextChanged
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.consumeEach
import kotlin.coroutines.CoroutineContext


class MainActivity : BaseActivity(), ViewModelOwner<MainState, MainRoute, MainAction>, CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    val viewModel: MainViewModel by viewModel()
    var searchEditText: EditText? = null

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
        searchEditText?.let { view ->
            launch {
                view.onTextChanged().debounce().consumeEach {
                    viewModel.searchHeroes(it)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)
        val searchItem = menu?.findItem(R.id.actionSearch)
        val searchManager = this@MainActivity.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = searchItem?.actionView as SearchView
        searchEditText = searchView.findViewById(
            searchView.context.resources
                .getIdentifier("android:id/search_src_text", null, null)
        )
        searchView.setSearchableInfo(searchManager.getSearchableInfo(this@MainActivity.componentName))
        return super.onCreateOptionsMenu(menu)
    }

    override fun onState(s: MainState?) {
        s?.let {
            when (it) {
                is MainState.Data -> {
                }
                is MainState.Loading -> {
                }
            }
        }
    }

    override fun onRoute(r: MainRoute?) {
        r?.let {
            when (it) {
                is MainRoute.HeroDetail -> {

                }
            }
        }

    }

    override fun onAction(r: MainAction?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}