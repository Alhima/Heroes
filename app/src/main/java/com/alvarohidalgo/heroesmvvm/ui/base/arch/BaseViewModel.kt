package com.alvarohidalgo.heroesmvvm.ui.base.arch

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<S : State, R : Route, A : Action> : ViewModel(), CoroutineScope {

    private val state: MutableLiveData<S> = MutableLiveData()
    private val route: MutableLiveData<R> = MutableLiveData()
    private val action: ActionLiveData<A> = ActionLiveData()

    private val job: Job = SupervisorJob()

    override val coroutineContext: CoroutineContext = job + Dispatchers.Main

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