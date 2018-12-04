package com.alvarohidalgo.heroesmvvm.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alvarohidalgo.heroesmvvm.R
import com.alvarohidalgo.heroesmvvm.ui.model.HeroeVM
import kotlinx.android.synthetic.main.item_heroe.view.*

class HeroeAdapter(val heroeList: MutableList<HeroeVM>, val heroeClickListener: HeroeClickListener) :
    RecyclerView.Adapter<HeroeAdapter.HeroeViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroeViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_heroe, parent, false)
        return HeroeViewHolder(v)
    }

    override fun getItemCount(): Int = heroeList.size

    override fun onBindViewHolder(holder: HeroeViewHolder, position: Int) {
        holder.bind(heroeList[position])
    }

    fun addHeroes(heroes: List<HeroeVM>) {
        heroeList.clear()
        heroeList.addAll(heroes)
        notifyDataSetChanged()
    }

    inner class HeroeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(heroe: HeroeVM) {
            with(itemView) {
                tvHeroName.transitionName = heroe.name
                ivHero.transitionName = heroe.photoUrl

                tvHeroName.text = heroe.name
                setOnClickListener { heroeClickListener.onHeroeClick(heroe) }
            }
        }
    }
}

interface HeroeClickListener {
    fun onHeroeClick(hero: HeroeVM)
}
