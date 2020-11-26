package com.jusang.randomcall.repository

import com.jusang.randomcall.entity.ContactEntity
import io.reactivex.Completable
import io.reactivex.Single

abstract class ContactRepository {
    abstract fun getContactList(): Single<List<ContactEntity>>
    abstract fun getLocalContactList(): Single<List<ContactEntity>>
    abstract fun insertContactList(contactEntities: List<ContactEntity>): Completable
}