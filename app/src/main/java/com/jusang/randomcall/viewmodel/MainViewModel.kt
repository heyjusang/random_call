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

    fun getContactList(): LiveData<List<ContactModel>> {
        mContactList.value = contactRepository.getContactList()
        return mContactList
    }
}