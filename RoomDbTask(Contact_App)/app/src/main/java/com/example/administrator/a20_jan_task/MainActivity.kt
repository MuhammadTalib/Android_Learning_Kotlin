package com.example.administrator.a20_jan_task

import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    companion object {
        var ContactData = ArrayList<contactdetails>()
        lateinit var db: AppDb
        var updateId = 0
        fun updateList()
        {
            ContactData.clear()
            ContactData.addAll(db.postsDao().getAll())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(
                this,
                AppDb::class.java,
                "ContactApp").allowMainThreadQueries().fallbackToDestructiveMigration().build()

        updateList()

        add.setOnClickListener {
            startActivity(Intent(this,AddContact::class.java))
            overridePendingTransition(R.anim.slide_digonal,0)
        }


        contactslist.adapter=ContactsAdapter(ContactData,::OnClicked)
        contactslist.layoutManager = LinearLayoutManager(this)
    }
    fun OnClicked(index:Int)
    {
        startActivity(Intent(this,ViewContact::class.java))
    }

}