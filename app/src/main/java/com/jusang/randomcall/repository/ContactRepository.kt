package com.jusang.randomcall.repository

import com.jusang.randomcall.model.ContactModel

abstract class ContactRepository {
    abstract fun getContactList(): List<ContactModel>
}