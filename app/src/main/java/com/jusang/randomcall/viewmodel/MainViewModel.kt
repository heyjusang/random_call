package com.jusang.randomcall.viewmodel

import androidx.lifecycle.MutableLiveData
import com.jusang.randomcall.entity.ContactEntity
import com.jusang.randomcall.repository.ContactRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    private val contactRepository: ContactRepository
): BaseViewModel<List<ContactEntity>>() {
    var randomContact: MutableLiveData<ContactEntity> = MutableLiveData()

    fun loadContactList() {
        startLoading()

        contactRepository.getContactList()
            .mergeWith(contactRepository.getLocalContactList())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = onDataLoadHandler,
                onError = onErrorHandler,
                onComplete = onCompleteHandler
            ).addTo(disposable)
    }

    fun selectRandomContact() {
        // TODO : how to hold loaded contact list ???
    }
}