package com.example.muhammadtalib.staggedrecyclerview

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class StaggedViewHolder(MyItem: View):RecyclerView.ViewHolder(MyItem)
{
    val myImage:ImageView=MyItem.findViewById(R.id.Image)
    val myText:TextView=MyItem.findViewById(R.id.Text)
}