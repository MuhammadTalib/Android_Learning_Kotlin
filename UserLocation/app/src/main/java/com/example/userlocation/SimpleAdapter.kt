package com.example.userlocation

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class SimpleAdapter(val data:ArrayList<users>) : RecyclerView.Adapter<MySimpleViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MySimpleViewHolder
    {
        val itemView  = LayoutInflater.from(parent.context).inflate(R.layout.user_item,parent,false)
        return MySimpleViewHolder(itemView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MySimpleViewHolder, position: Int) {
        holder.mynameTv.text = data[position].Name
        holder.mynumberTv.text= data[position].PhNumber
        holder.mylatTv.text= data[position].location?.latitude.toString()
        holder.mylongTv.text= data[position].location?.longitude.toString()
    }
}