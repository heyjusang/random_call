package com.jusang.randomcall.database;

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jusang.randomcall.entity.ContactEntity

@Database(entities = [ContactEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contactDao() : ContactDao
}
