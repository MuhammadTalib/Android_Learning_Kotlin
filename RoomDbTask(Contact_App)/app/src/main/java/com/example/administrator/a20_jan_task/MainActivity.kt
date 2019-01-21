package com.example.administrator.a20_jan_task

import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        var ContactData = ArrayList<contactdetails>()
    }
    lateinit var db: AppDb
    var updateId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        done.setOnClickListener{
            var Contact:contactdetails= contactdetails()
            Contact.Contact_Name=Name.text.toString()

                save(Contact)

            }


        db = Room.databaseBuilder(
                this,
                AppDb::class.java,
                "ContactApp").allowMainThreadQueries().fallbackToDestructiveMigration().build()


        updateList()


    }

    private fun updateList(){
        ContactData.clear()
        ContactData.addAll(db.postsDao().getAll())
    }

    private fun save(text:contactdetails){
        if(updateId == 0){
            db.postsDao().insert(contactdetails(text.Contact_Name,text.Contact_Number,text.Contact_Type,text.Address))
        }else{
            db.postsDao().update(contactdetails(text.Contact_Name,text.Contact_Number,text.Contact_Type,text.Address))
        }
        updateId = 0
        updateList()
    }
}