package com.example.talib.firebasedbyoutube

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var databaseArtists:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseArtists=FirebaseDatabase.getInstance().getReference("artists")

        button.setOnClickListener {
            addArtist()
        }
    }
    fun addArtist()
    {
        var name:String=text1.text.toString()
        //var genre:String=spinner.selectedItem.toString()
        if(!TextUtils.isEmpty(name))
        {
           var id:String?=databaseArtists.push().key
           var artist:Artist= Artist(id!!,name,"12")
            databaseArtists.child(id).setValue(artist)

            Toast.makeText(this,"Artist Added",Toast.LENGTH_SHORT).show()
        }
        else
        {
            Toast.makeText(this,"Enter Something Please!!",Toast.LENGTH_SHORT).show()
        }

    }
}
