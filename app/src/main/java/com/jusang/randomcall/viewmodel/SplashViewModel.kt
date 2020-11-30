package com.jusang.randomcall.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import com.jusang.randomcall.R
import com.jusang.randomcall.entity.ContactEntity
import com.jusang.randomcall.repository.ContactRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class SplashViewModel @ViewModelInject constructor(
    private val contactRepository: ContactRepository
): BaseViewModel<Nothing>() {

    fun populateData() {
        val contactEntities: ArrayList<ContactEntity> = arrayListOf()

        for (i in 1..9) {
            contactEntities.add(ContactEntity(i, "name$i", "010-1234-432$i", R.drawable.profile_sample))
        }

        contactRepository.insertContactList(contactEntities)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onComplete = onCompleteHandler, onError = onErrorHandler )
            .addTo(disposable)
    }
}