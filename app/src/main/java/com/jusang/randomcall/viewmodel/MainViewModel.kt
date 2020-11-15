package com.jusang.randomcall.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jusang.randomcall.model.ContactModel
import com.jusang.randomcall.model.State
import com.jusang.randomcall.repository.ContactRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    private val contactRepository: ContactRepository
): BaseViewModel<List<ContactModel>>() {
    var randomContact: MutableLiveData<ContactModel> = MutableLiveData()

    fun loadContactList() {
        startLoading()

        contactRepository.getContactList()
            .subscribeOn(Schedulers.io()) // which schedulers?
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onSuccess = onSuccessHandler, onError = onErrorHandler)
            .addTo(disposable)
    }

    fun selectRandomContact() {
        // TODO : how to hold loaded contact list ???
    }
}