package com.example.userlocation

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_user_list.*

class userList : AppCompatActivity() {

    companion object {

        var LatLan: LatLng?=null

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        userlistview.adapter=SimpleAdapter(MainActivity.userlist,::openMap)
        userlistview.layoutManager=LinearLayoutManager(this)
    }
    fun openMap(index:Int){
        LatLan=MainActivity.userlist[index].location
        val i=Intent(this, UserLocation::class.java)
        startActivity(i)
    }
}
