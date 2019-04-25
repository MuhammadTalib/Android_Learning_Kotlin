package com.example.userlocation

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class MySimpleViewHolder(myItem: View) : RecyclerView.ViewHolder(myItem) {
    val mynameTv : TextView = myItem.findViewById(R.id.username)
    val mynumberTv : TextView = myItem.findViewById(R.id.usernumber)
    val mylatTv : TextView = myItem.findViewById(R.id.userlatitude)
    val mylongTv : TextView = myItem.findViewById(R.id.userlongitude)
    val myaddress:TextView=myItem.findViewById(R.id.useraddress)
    val myaddlink:TextView=myItem.findViewById(R.id.useraddlink)
}