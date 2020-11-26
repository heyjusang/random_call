package com.jusang.randomcall.entity;

import androidx.room.ColumnInfo
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contacts")
data class ContactEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val Name: String,
    @ColumnInfo(name = "phone") val Phone: String,
    @ColumnInfo(name = "photo_id") val PhotoId: Int?
)