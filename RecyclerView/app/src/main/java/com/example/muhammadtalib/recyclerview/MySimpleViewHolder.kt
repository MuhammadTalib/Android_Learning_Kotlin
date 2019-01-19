package com.example.muhammadtalib.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class MySimpleViewHolder(myItem: View) : RecyclerView.ViewHolder(myItem) {
    val myTv : TextView = myItem.findViewById(R.id.MySimpleTextId)
}