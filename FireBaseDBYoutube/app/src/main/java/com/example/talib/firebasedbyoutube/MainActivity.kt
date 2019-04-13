package com.example.talib.firebasedbyoutube

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity(),ChildEventListener {


    lateinit var databaseArtists:DatabaseReference
    lateinit var db:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseArtists=FirebaseDatabase.getInstance().getReference("artists")
        //databaseArtists.addChildEventListener(this)

        button.setOnClickListener {
            addArtist()
        }
        db = FirebaseDatabase.getInstance().getReference("artists")
        db.addChildEventListener(object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                Log.e("hahaha", "addded")
                val fbuser = p0.getValue(Artist::class.java)
                Toast.makeText(this@MainActivity, fbuser?.ArtistName, Toast.LENGTH_SHORT).show()

            }
            override fun onChildRemoved(p0: DataSnapshot) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }
    fun addArtist()
    {
        var name:String=text1.text.toString()
        //var genre:String=spinner.selectedItem.toString()
        if(!TextUtils.isEmpty(name))
        {
           var id:String?=databaseArtists.push().key
           var artist:Artist= Artist().apply {
               this.ArtistId=id!!
               this.ArtistName=name
               this.ArtistAge="12"
           }
            databaseArtists.child(id!!).setValue(artist)

            Toast.makeText(this,"Artist Added",Toast.LENGTH_SHORT).show()
        }
        else
        {
            Toast.makeText(this,"Enter Something Please!!",Toast.LENGTH_SHORT).show()
        }

    }
    override fun onCancelled(p0: DatabaseError) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onChildMoved(p0: DataSnapshot, p1: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onChildChanged(p0: DataSnapshot, p1: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onChildAdded(p0: DataSnapshot, p1: String?) {
        val fbuser = p0.getValue(Artist::class.java)
        if(fbuser!=null)
            Toast.makeText(this,fbuser?.ArtistName,Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(this,"aaaa",Toast.LENGTH_SHORT).show()

    }

    override fun onChildRemoved(p0: DataSnapshot) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
