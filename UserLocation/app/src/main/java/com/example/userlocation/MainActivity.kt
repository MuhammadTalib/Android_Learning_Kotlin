package com.example.userlocation

import android.content.Intent
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        var users:ArrayList<users>?=null
        var Name:String=""
        var Location: LatLng?=null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        open_map.setOnClickListener{
            if(name.text.toString()!="") {
                Name = name.text.toString()
                startActivity(Intent(this, MapsActivity::class.java))
            }
            else
            {
                name.error="Please Fill the name"
            }
        }

    }
}
