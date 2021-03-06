package com.example.muhammadtalib.friendsbook

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class FriendViewHolder(MyItem: View): RecyclerView.ViewHolder(MyItem)
{
    val myImage: ImageView =MyItem.findViewById(R.id.image)
    val myText: TextView =MyItem.findViewById(R.id.name)
    val myStatus: TextView =MyItem.findViewById(R.id.status)
}