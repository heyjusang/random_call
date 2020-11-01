package com.jusang.randomcall.viewmodel

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jusang.randomcall.model.ContactModel
import com.jusang.randomcall.repository.ContactRepository
import com.jusang.randomcall.repository.LocalContactRepository

class MainViewModel(
    private val contactRepository: ContactRepository
): ViewModel() {
    private var mContactList: MutableLiveData<List<ContactModel>> = MutableLiveData()
    private var mRandomContact: MutableLiveData<ContactModel> = MutableLiveData()

    fun getContactList(): LiveData<List<ContactModel>> {
        mContactList.value = contactRepository.getContactList()
        return mContactList
    }

    fun getRandomContact(): LiveData<ContactModel> {
        return mRandomContact
    }

    fun selectRandomContact() {
        val length = mContactList.value?.size ?: 0

        if (length > 0) {
            val index = (0 until length).random()
            mRandomContact.value = mContactList.value?.get(index)
        }
    }
}