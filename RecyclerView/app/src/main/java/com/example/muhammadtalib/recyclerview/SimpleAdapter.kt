package com.example.muhammadtalib.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class SimpleAdapter(val itemViewFile:Int,
                    val data:ArrayList<String>,
                    val onItemClick:(Int)->Unit) : RecyclerView.Adapter<MySimpleViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MySimpleViewHolder
    {
        val itemView  = LayoutInflater.from(parent.context).inflate(itemViewFile,parent,false)
        return MySimpleViewHolder(itemView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MySimpleViewHolder, position: Int) {
        holder.myTv.text = data[position]
        holder.itemView.setOnClickListener {
            onItemClick(position)

        }
    }
}