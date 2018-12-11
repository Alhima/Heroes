package com.alvarohidalgo.heroesmvvm.ui.base.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alvarohidalgo.heroesmvvm.R
import kotlinx.android.synthetic.main.widget_infinitescroll.view.*
import com.alvarohidalgo.heroesmvvm.ui.base.extensions.gone


class InfiniteDataCardScrollView<T : RecyclerView.Adapter<RecyclerView.ViewHolder>> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defaultStyle: Int = 0
) : RelativeLayout(context, attrs, defaultStyle) {

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.InfiniteDataCardScrollView, defaultStyle, 0)
        val t = a.getString(R.styleable.InfiniteDataCardScrollView_title)
        a.recycle()
        LayoutInflater.from(context).inflate(R.layout.widget_infinitescroll, this, false)
        t?.let { titleText.text = it } ?: titleText.gone()
    }

    fun setAdapter(adapter: T) {
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter
    }

    fun setTitle(title: String) {
        titleText.text = title
    }
}