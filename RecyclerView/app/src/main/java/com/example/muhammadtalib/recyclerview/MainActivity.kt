package com.example.muhammadtalib.recyclerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import android.content.Context
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myRecyclerList.layoutManager=LinearLayoutManager(this,LinearLayout.VERTICAL,false)
        myRecyclerList.adapter=SimpleAdapter(R.layout.simpleitemview,arrayListOf(
                "Item 1","Item 2","Item 3","Item 4","Item 1","Item 2","Item 3","Item 4","Item 1","Item 2","Item 3","Item 4","Item 1","Item 2","Item 3","Item 4"
        ),::onItemClick)
    }

    fun onItemClick(position:Int)
    {
        t("clicked on $position")
    }
}

