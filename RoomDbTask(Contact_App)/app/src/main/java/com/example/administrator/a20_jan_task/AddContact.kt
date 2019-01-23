package com.example.administrator.a20_jan_task

import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_add_contact.*
import kotlinx.android.synthetic.main.activity_main.*

class AddContact : AppCompatActivity() {

    var Contact_Type_List= arrayListOf<String>("Mobile","Work","Home","Main","Work Fax","Home Fax","Pager","Other","Custom")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        cancel.setOnClickListener {

        }
        done.setOnClickListener{

            var Contact:contactdetails= contactdetails()
            if(Name.text.toString().isEmpty()){
                Name.error = "Please Enter Some Text!"
                return@setOnClickListener
            }
            Contact.Contact_Name=Name.text.toString()
            if(Number.text.toString().isEmpty()) Contact.Contact_Number=0 else Contact.Contact_Number=Integer.parseInt(Number.text.toString())
            Contact.Contact_Type=""
            Contact.Address=Addressdetails()
            Contact.Address.type="Home"
            if(House_No.text.toString().isEmpty()) Contact.Address.House_No="" else Contact.Address.House_No=House_No.text.toString()
            if(Street.text.toString().isEmpty()) Contact.Address.Block="" else Contact.Address.Block=Street.text.toString()
            if(City.text.toString().isEmpty()) Contact.Address.City="" else Contact.Address.City=City.text.toString()
            if(Country.text.toString().isEmpty())Contact.Address.Country="" else Contact.Address.Country=Country.text.toString()
            if(Email.text.toString().isEmpty())Contact.Address.Email="" else Contact.Address.Email=Email.text.toString()


            save(Contact)

            Name.setText("")
            Number.setText("")
            House_No.setText("")
            Street.setText("")
            City.setText("")
            Country.setText("")
            Email.setText("")
        }
        conatact_type_drop.setOnClickListener {

            val dialog = CustomDialog(this,Contact_Type_List)
            dialog.setCancelable(true)
            dialog.show()


        }




        Log.e("aaa","asd")


    }
    override fun onBackPressed()
    {
        startActivity(Intent(this,MainActivity::class.java))
        overridePendingTransition(R.anim.slide_digonal_down,0)
    }
    fun updateList()
    {
        MainActivity.ContactData.clear()
        MainActivity.ContactData.addAll(MainActivity.db.postsDao().getAll())
    }
    private fun save(text:contactdetails){
        if(MainActivity.updateId == 0){
            MainActivity.db.postsDao().insert(text)
        }else{
            MainActivity.db.postsDao().update(contactdetails(text.Contact_Name,text.Contact_Number,text.Contact_Type,text.Address))
        }
        MainActivity.updateId = 0
        updateList()
    }
}