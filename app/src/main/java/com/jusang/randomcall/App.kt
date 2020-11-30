package com.jusang.randomcall

import android.app.Application
import androidx.room.Room
import com.jusang.randomcall.database.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    companion object {
        var db: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        App.db = Room.databaseBuilder(this, AppDatabase::class.java, "random_call.db").build()
    }
}