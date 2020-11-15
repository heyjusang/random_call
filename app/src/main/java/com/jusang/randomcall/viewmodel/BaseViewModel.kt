package com.jusang.randomcall.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jusang.randomcall.model.State
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel<T>: ViewModel() {
    protected var disposable = CompositeDisposable()
    protected val mResult = MutableLiveData<State<T>>()
    val result: LiveData<State<T>>
        get() = mResult

    protected val onErrorHandler: (Throwable) -> Unit = {
        mResult.value = State.Error(it.message ?: "Failed to load data")
    }

    protected val onDataLoadHandler: (T) -> Unit = {
        mResult.value = State.Loaded(it)
    }

    protected val onCompleteHandler: () -> Unit = {
        mResult.value = State.Complete
    }

    protected val onSuccessHandler: (T) -> Unit = {
        onDataLoadHandler(it)
        onCompleteHandler()
    }

    protected fun startLoading() {
        disposeCurrentlyRunningStreams()
        mResult.value = State.Loading
    }

    private fun disposeCurrentlyRunningStreams() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}