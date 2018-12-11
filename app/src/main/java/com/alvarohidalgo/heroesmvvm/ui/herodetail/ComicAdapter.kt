package com.alvarohidalgo.heroesmvvm.ui.herodetail

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alvarohidalgo.heroesmvvm.R
import com.alvarohidalgo.heroesmvvm.ui.base.extensions.download
import com.alvarohidalgo.heroesmvvm.ui.model.ComicVM
import kotlinx.android.synthetic.main.item_comic.view.*

class ComicAdapter(val action: (String) -> Unit) : RecyclerView.Adapter<ComicAdapter.ComicViewHolder>() {

    private val comics: MutableList<ComicVM> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val v = View.inflate(parent.context, R.layout.item_comic, parent)
        return ComicViewHolder(v)
    }

    override fun getItemCount(): Int = comics.size

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        holder.bind(comics[position])
    }

    fun setComics(moreComics: List<ComicVM>) {
        comics.addAll(moreComics)
        notifyDataSetChanged()
    }

    inner class ComicViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bind(comic: ComicVM) {
            with(itemView) {
                comicContainer.setOnClickListener { action.invoke(comic.id) }
                comicImage.download(comic.url)
            }
        }
    }
}