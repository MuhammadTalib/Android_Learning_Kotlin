package com.example.muhammadtalib.staggedrecyclerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MyStagged.layoutManager = LinearLayoutManager( this,LinearLayout.VERTICAL,false)

        var post1=post(R.drawable.img1,"Talib")
        var post2=post(R.drawable.ic_launcher_foreground,"waseem")
        var post3=post(R.drawable.ic_launcher_background,"Tariq")

        MyStagged.adapter = StaggedAdapter(arrayListOf(
        post1,post2,post3
        ),::onItemClick)
    }

    fun onItemClick(position:Int){
        t("clicked on $position")
    }

}
