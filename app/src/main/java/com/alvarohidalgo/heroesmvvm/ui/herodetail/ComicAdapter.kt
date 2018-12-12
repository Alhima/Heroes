package com.alvarohidalgo.heroesmvvm.ui.herodetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alvarohidalgo.heroesmvvm.R
import com.alvarohidalgo.heroesmvvm.ui.base.extensions.download
import com.alvarohidalgo.heroesmvvm.ui.model.ComicVM
import kotlinx.android.synthetic.main.item_comic.view.*

class ComicAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val comics: MutableList<ComicVM> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_comic, parent,false)
        return ComicViewHolder(v)
    }

    override fun getItemCount(): Int = comics.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ComicViewHolder).bind(comics[position])
    }

    fun setComics(moreComics: List<ComicVM>) {
        comics.addAll(moreComics)
        notifyDataSetChanged()
    }

    inner class ComicViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bind(comic: ComicVM) {
            with(itemView) {
                comicName.text = comic.name
            }
        }
    }
}