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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        done.setOnClickListener{
            var Contact:contactdetails= contactdetails()
            Contact.Contact_Name=Name.text.toString()
            Contact.Contact_Number=Number.text.toString()
            Contact.Address=Addressdetails("Home",House_No.text.toString(),Street.text.toString(),City.text.toString(),Country.text.toString(),Email.text.toString())

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
        ContactData.addAll(db.ContactsDao().getAll())
    }

    private fun save(text:contactdetails){

        db.ContactsDao().insert(contactdetails(text.Contact_Name,text.Contact_Number,text.Contact_Type,text.Address))
        updateList()
    }
}