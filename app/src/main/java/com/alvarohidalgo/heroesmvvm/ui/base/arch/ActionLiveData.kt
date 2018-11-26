package com.alvarohidalgo.heroesmvvm.ui.base.arch

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.support.annotation.MainThread

class ActionLiveData<A : Action> : MutableLiveData<A>() {

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<A>) {
        if (hasObservers()) {
            throw Throwable("Only one observer allowed on an ActionLiveData")
        }
        super.observe(owner, Observer { data ->
            if (data == null) return@Observer
            observer.onChanged(data)
            value = null
        })
    }

    @MainThread
    fun sendAction(data: A) {
        value = data
    }
}