package com.example.muhammadtalib.friendsbook

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class PostViewHolder(MyItem: View): RecyclerView.ViewHolder(MyItem)
{
    val Image: ImageView =MyItem.findViewById(R.id.postimage)
    val myText: TextView =MyItem.findViewById(R.id.postcontent)
}