package com.example.administrator.a20_jan_task

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

@Dao
interface ContactsDao {

    @Query("SELECT * FROM contactdetails WHERE Contact_Number = :id")
    fun getById(id:Int):contactdetails

    @Query("SELECT * FROM contactdetails ORDER BY Contact_Number DESC")
    fun getAll():List<contactdetails>

    @Insert
    fun insert(post: contactdetails)

    @Update
    fun update(post: contactdetails)

}