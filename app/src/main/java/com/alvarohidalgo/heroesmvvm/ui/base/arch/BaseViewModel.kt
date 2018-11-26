package com.alvarohidalgo.heroesmvvm.ui.base.arch

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import com.alvarohidalgo.heroesmvvm.ui.base.coroutines.DispatchersConfig
import com.alvarohidalgo.heroesmvvm.ui.base.coroutines.DispatchersConfigImpl
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<S : State, R : Route, A : Action>(dispatcher: DispatchersConfig = DispatchersConfigImpl()) :
    ViewModel(), CoroutineScope {

    private val state: MutableLiveData<S> = MutableLiveData()
    private val route: MutableLiveData<R> = MutableLiveData()
    private val action: ActionLiveData<A> = ActionLiveData()

    private val job: Job = SupervisorJob()

    override val coroutineContext: CoroutineContext = job + dispatcher.main

    fun runFirstTime(block: () -> Unit) {
        if (state.value == null) {
            block()
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun observe(lifecycleOwner: LifecycleOwner) {
        (lifecycleOwner as? ViewModelOwner<S, R, A>)?.let { owner ->
            state.observe(lifecycleOwner, Observer { owner.onState(it) })
            route.observe(lifecycleOwner, Observer { owner.onRoute(it) })
            action.observe(lifecycleOwner, Observer { owner.onAction(it) })
        }

    }

    protected fun changeState(s: S) {
        state.postValue(s)
    }

    protected fun changeRoute(r: R) {
        route.postValue(r)
    }

    protected fun triggerAction(a: A) {
        action.postValue(a)
    }

    protected fun runJob(
        handler: ErrorHandler? = null,
        block: suspend CoroutineScope.() -> Unit
    ) {
        val errorHandler = CoroutineExceptionHandler { _, exception ->
            handler?.onError(exception)
        }

        launch(coroutineContext + errorHandler) {
            block()
        }
    }

    interface ErrorHandler {
        fun onError(exception: Throwable)
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}