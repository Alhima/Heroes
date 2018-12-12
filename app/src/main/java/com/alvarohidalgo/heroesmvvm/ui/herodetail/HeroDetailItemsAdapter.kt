package com.alvarohidalgo.heroesmvvm.ui.herodetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alvarohidalgo.heroesmvvm.R
import com.alvarohidalgo.heroesmvvm.ui.model.HeroDetailItem
import kotlinx.android.synthetic.main.item_comic_list.view.*
import kotlinx.android.synthetic.main.item_description.view.*

class HeroDetailItemsAdapter(
    private val listItems: MutableList<HeroDetailItem>,
    private val onClickItemListener: OnClickItemListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnClickItemListener {
        fun onClick(item: HeroDetailItem)
    }

    companion object {
        private const val DESCRIPTION = 0
        private const val COMICLIST = 1
        private const val STORYLIST = 2
        private const val EVENTLIST = 3
        private const val SERIESLIST = 4
    }

    override fun getItemViewType(position: Int): Int {
        return when (listItems[position]) {
            is HeroDetailItem.Description -> DESCRIPTION
            is HeroDetailItem.ComicList -> COMICLIST
            is HeroDetailItem.EventList -> STORYLIST
            is HeroDetailItem.SerieList -> EVENTLIST
            is HeroDetailItem.StoryList -> SERIESLIST
        }
    }

    fun addItems(list: List<HeroDetailItem>) {
        listItems.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            DESCRIPTION -> DescriptionViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_description,
                    parent,
                    false
                )
            )
            COMICLIST -> ComicsViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_comic_list,
                    parent,
                    false
                )
            )
            STORYLIST -> DescriptionViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_description,
                    parent,
                    false
                )
            )
            EVENTLIST -> DescriptionViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_description,
                    parent,
                    false
                )
            )
            SERIESLIST -> DescriptionViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_description,
                    parent,
                    false
                )
            )
            else -> DescriptionViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_description,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DescriptionViewHolder -> holder.bind(
                listItems[position] as HeroDetailItem.Description,
                onClickItemListener
            )
            is ComicsViewHolder -> holder.bind(
                listItems[position] as HeroDetailItem.ComicList
            )
        }
    }

    inner class DescriptionViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bind(description: HeroDetailItem.Description, onClickItem: OnClickItemListener) {
            with(itemView) {
                descriptionText.text = description.descriptionText
                setOnClickListener { onClickItem.onClick(description) }
            }
        }
    }


    inner class ComicsViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bind(comicListEntity: HeroDetailItem.ComicList) {
            val comicList = comicListEntity.list
            val adapter = ComicAdapter()
            adapter.setComics(comicList)
            with(itemView) {
                titleText.text = context.getString(R.string.comics)
                recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                recyclerView.adapter = adapter
            }
        }
    }
}