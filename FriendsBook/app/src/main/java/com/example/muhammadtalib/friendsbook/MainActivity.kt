package com.example.muhammadtalib.friendsbook

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import java.net.URI
import android.provider.MediaStore
import android.net.Uri
import android.content.Intent.ACTION_VIEW
import android.provider.ContactsContract
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.app.Activity
import android.widget.*
import com.example.muhammadtalib.friendsbook.R.id.*
import kotlinx.android.synthetic.main.activity_friend_list.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        val eemail = "myemail"
    }

    val MY_KEY = 1
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.hide();

        var Countriesdata=ArrayList<Country>()
        Countriesdata.add(Country("Pakistan",R.drawable.pakistan))
        Countriesdata.add(Country("Bangladesh",R.drawable.bangladesh))
        Countriesdata.add(Country("Iran",R.drawable.iran))
        Countriesdata.add(Country("China",R.drawable.china))
        Countriesdata.add(Country("Turkey",R.drawable.turkey))
        Countriesdata.add(Country("India",R.drawable.india))

        var spinnerAdapter: SpinnerCustomDropdownAdapter = SpinnerCustomDropdownAdapter(this,Countriesdata)
        var spinner: Spinner = findViewById(R.id.CountrySpinner) as Spinner
        spinner.adapter = spinnerAdapter


     //   spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        //    override fun onNothingSelected(parent: AdapterView<*>?) {
        //    }}



            login.setOnClickListener{

            val i=emailinput.text.toString()
            val intent=Intent(this,FriendList::class.java)
            intent.putExtra(eemail,i);
            startActivityForResult(intent,MY_KEY)

        }

    }

    fun facebook(view:View)
    {

        val uri=Uri.parse("https://www.facebook.com/")
        val intent=Intent(Intent.ACTION_VIEW,uri)
        startActivity(intent)

    }
    fun Google(view:View)
    {

        val uri=Uri.parse("https://www.google.com/")
        val intent=Intent(Intent.ACTION_VIEW,uri)
        startActivity(intent)
    }
    fun Googleplus(view:View)
    {

        val uri=Uri.parse("https://plus.google.com/")
        val intent=Intent(Intent.ACTION_VIEW,uri)
        startActivity(intent)
    }



}
