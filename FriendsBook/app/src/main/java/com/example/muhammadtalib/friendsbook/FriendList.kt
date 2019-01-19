package com.example.muhammadtalib.friendsbook

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toolbar

import kotlinx.android.synthetic.main.activity_friend_list.*
import kotlinx.android.synthetic.main.activity_main.*


class FriendList : AppCompatActivity() {

    companion object {
        var str:String="sfst"
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_list)
        getSupportActionBar()?.hide();

        FriendListRecycler.layoutManager = LinearLayoutManager( this, LinearLayout.VERTICAL,false)

        var Friend1=Friend("waseem","active",R.drawable.ic_launcher_foreground)
        var Friend2=Friend("waseem","inactive",R.drawable.ic_launcher_foreground)
        var Friend3=Friend("waseem","active",R.drawable.ic_launcher_foreground)

        FriendListRecycler.adapter = FriendListAdapter(arrayListOf(
                Friend1,Friend2,Friend3
        ),::onItemClick)


    }
    fun onItemClick(position:Int){
        t("clicked on $position")
    }

    fun profile(view:View)
    {
        val intent= Intent(this,UserProfile::class.java)
        startActivity(intent)
    }
    fun friends(view: View)
    {

    }
    fun home(view:View)
    {
        val dtst:String=intent.getStringExtra(MainActivity.eemail)
        str=intent.getStringExtra(MainActivity.eemail)
        val intent= Intent(this,Home::class.java)
        intent.putExtra(str,dtst)
        startActivity(intent)
    }
    fun addFriends(view:View)
    {
    }

}
