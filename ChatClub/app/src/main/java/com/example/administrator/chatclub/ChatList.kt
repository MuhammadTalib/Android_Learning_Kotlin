package com.example.administrator.chatclub

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.administrator.chatclub.MainPage.Companion.MyAccount
import kotlinx.android.synthetic.main.activity_chat_list.*
import android.graphics.drawable.Drawable
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast


class ChatList :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userImage.setImageResource(MyAccount.Image)
        MyChatList.layoutManager = LinearLayoutManager( this, LinearLayout.VERTICAL,false)
        MyChatList.adapter = ChatListAdapter(MainPage.AccountData,::onItemClick)



    }
    fun onItemClick(position:Int)
    {
        t("clicked on $position")
    }
}





