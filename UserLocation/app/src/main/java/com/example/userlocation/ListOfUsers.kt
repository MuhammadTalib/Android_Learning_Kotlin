package com.example.userlocation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_list_of_users.*

class ListOfUsers : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_users)

        userlist.layoutManager= LinearLayoutManager(this, LinearLayout.VERTICAL,false)
        userlist.adapter=SimpleAdapter(MainActivity.users!!)

    }
}
