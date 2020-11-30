package com.jusang.randomcall.repository

import com.jusang.randomcall.entity.ContactEntity
import io.reactivex.Completable
import io.reactivex.Single

interface ContactRepository {
    fun getContactList(): Single<List<ContactEntity>>
    fun getLocalContactList(): Single<List<ContactEntity>>
    fun insertContactList(contactEntities: List<ContactEntity>): Completable
}