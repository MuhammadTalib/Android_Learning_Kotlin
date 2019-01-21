package com.example.talib.roomdatabase

import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
            this,
            AppDB::class.java,
            "MyContactsDB").allowMainThreadQueries().fallbackToDestructiveMigration().build()

        saveBtn.setOnClickListener {
            db.getContactsDao().insert(ContactItem(0,nameEt.text.toString(),phoneEt.text.toString()))
            nameEt.setText("")
            phoneEt.setText("")
        }
    }
}