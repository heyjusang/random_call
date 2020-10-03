package com.jusang.randomcall.viewmodel

import android.Manifest
import android.app.Activity
import android.app.Application
import android.content.pm.PackageManager
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jusang.randomcall.model.ContactModel
import com.jusang.randomcall.repository.ContactRepository

class MainViewModel(application: Application): AndroidViewModel(application) {
    private var mContactRepository: ContactRepository = ContactRepository(application.applicationContext)
    private lateinit var mContactList: MutableLiveData<List<ContactModel>>

    fun getContactList(): LiveData<List<ContactModel>> {
        if (!::mContactList.isInitialized) {
            mContactList = MutableLiveData()
            mContactList.value = loadContactList()
        }

        return mContactList
    }

    private fun loadContactList(): List<ContactModel> {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
            getApplication<Application>().checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ArrayList()
        } else {
            mContactRepository.getContactList()
        }
    }

    fun onRequestReadContactsPermissionsResult(result: Int) {
        if (result == PackageManager.PERMISSION_GRANTED) {
            mContactList.value = mContactRepository.getContactList()
        }
        else {
            // TODO
        }
    }






}