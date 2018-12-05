package com.alvarohidalgo.heroesmvvm.ui.base.extensions

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception

class NotLinearLayoutException : Exception()

fun RecyclerView.onPageTriggered(isStopped: () -> Boolean, isloading: () -> Boolean, action: () -> Unit) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (layoutManager !is LinearLayoutManager) throw NotLinearLayoutException()
            (layoutManager as? LinearLayoutManager)?.let {
                val totalItemCount = it.itemCount
                val lastVisibleItemPosition = it.findLastVisibleItemPosition()
                if (!isloading() && !isStopped() && lastVisibleItemPosition >= totalItemCount - 3) {
                    action.invoke()
                }
            }

        }
    })
}