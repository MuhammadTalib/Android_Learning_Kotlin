package com.example.talib.roomdatabase

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [ContactItem::class],version = 1)
abstract class AppDB : RoomDatabase() {
    abstract fun getContactsDao():ContactDao
}