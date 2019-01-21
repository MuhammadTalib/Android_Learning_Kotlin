package com.example.talib.roomdatabase2

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Post(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    @ColumnInfo val text:String,
    @ColumnInfo val comments:Array<String>,
    @Embedded(prefix = "user_") val user: PostUser
)