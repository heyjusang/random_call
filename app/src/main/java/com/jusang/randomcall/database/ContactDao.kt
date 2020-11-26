package com.jusang.randomcall.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jusang.randomcall.entity.ContactEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ContactDao {

    @Query("SELECT * FROM CONTACTS")
    fun getAll(): Single<List<ContactEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun bulkInsert(contactEntities: List<ContactEntity>): Completable
}