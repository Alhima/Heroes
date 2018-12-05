package com.alvarohidalgo.heroesmvvm.ui.main

import android.os.Bundle
import android.view.Menu
import com.alvarohidalgo.heroesmvvm.R
import com.alvarohidalgo.heroesmvvm.ui.base.BaseActivity
import com.alvarohidalgo.heroesmvvm.ui.base.arch.ViewModelOwner
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import com.alvarohidalgo.heroesmvvm.navigation.Navigation
import com.alvarohidalgo.heroesmvvm.ui.base.extensions.debounce
import com.alvarohidalgo.heroesmvvm.ui.base.extensions.onTextChanged
import com.alvarohidalgo.heroesmvvm.ui.herodetail.HeroDetailActivity
import com.alvarohidalgo.heroesmvvm.ui.model.HeroeVM
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.consumeEach
import kotlin.coroutines.CoroutineContext
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.alvarohidalgo.heroesmvvm.ui.base.extensions.onPageTriggered
import kotlinx.android.synthetic.main.item_heroe.*


class MainActivity : BaseActivity(), ViewModelOwner<MainState, MainRoute, MainAction>, CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default

    val viewModel: MainViewModel by viewModel()
    var searchEditText: EditText? = null

    val heroeClickListener: HeroeClickListener = object : HeroeClickListener {
        override fun onHeroeClick(hero: HeroeVM) {
            viewModel.heroClicked(hero)
        }
    }

    val heroesAdapter: HeroeAdapter = HeroeAdapter(mutableListOf(), heroeClickListener)

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        heroesrecyclerView.layoutManager = GridLayoutManager(this, 2)
        heroesrecyclerView.adapter = heroesAdapter
        heroesrecyclerView.onPageTriggered(viewModel.isPaginatedListFull(), viewModel.isLoading(), viewModel.loadNextPage())
        viewModel.observe(this)
        viewModel.initScreen()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)
        val searchItem = menu?.findItem(R.id.actionSearch)
        val searchManager = this@MainActivity.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = searchItem?.actionView as SearchView
        searchEditText = searchView.findViewById(R.id.search_src_text)
        searchEditText?.let { view ->
            launch {
                view.onTextChanged().debounce().consumeEach {
                    viewModel.searchHeroes(it)
                }
            }
        }
        searchView.setSearchableInfo(searchManager.getSearchableInfo(this@MainActivity.componentName))
        return super.onCreateOptionsMenu(menu)
    }

    override fun onState(s: MainState?) {
        s?.let {
            when (it) {
                is MainState.Data -> {
                    heroesAdapter.addHeroes(it.heroes)
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
                    val p1 = androidx.core.util.Pair<View, String>(ivHero, it.hero.photoUrl)
                    val p2 = androidx.core.util.Pair<View, String>(tvHeroName, it.hero.name)
                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, p1, p2)

                    startActivity(Intent(this, HeroDetailActivity::class.java).apply {
                        putExtras(Bundle().apply {
                            putString(Navigation.HeroDetail.BUNDLE_HERO_ID, it.hero.id)
                            putString(Navigation.HeroDetail.BUNDLE_HERO_URL, it.hero.photoUrl)
                            putString(Navigation.HeroDetail.BUNDLE_HERO_NAME, it.hero.name)
                        })
                    }, options.toBundle())
                }
            }
        }

    }

    override fun onAction(r: MainAction?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}