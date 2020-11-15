package com.jusang.randomcall.repository

import com.jusang.randomcall.model.ContactModel
import io.reactivex.Single

abstract class ContactRepository {
    abstract fun getContactList(): Single<List<ContactModel>>
}