package com.alvarohidalgo.heroesmvvm.ui.base.arch

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer


class ActionLiveData<A : Action> : MutableLiveData<A>() {

    override fun observe(owner: LifecycleOwner, observer: Observer<in A>) {
        if (hasObservers()) {
            throw Throwable("Only one observer allowed on an ActionLiveData")
        }
        super.observe(owner, Observer { data ->
            if (data == null) return@Observer
            observer.onChanged(data)
            value = null
        })

    }

    fun sendAction(data: A) {
        value = data
    }
}