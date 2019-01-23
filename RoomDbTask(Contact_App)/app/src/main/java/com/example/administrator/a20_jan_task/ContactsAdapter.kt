package com.example.administrator.a20_jan_task

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class ContactsAdapter(val data:ArrayList<contactdetails>):RecyclerView.Adapter<ContactsViewHolder>()
{

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ContactsViewHolder {
        val itemView  = LayoutInflater.from(p0.context).inflate(R.layout.contact_list_item,p0,false)
        return ContactsViewHolder(itemView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(p0: ContactsViewHolder, p1: Int) {
       p0.bind(data[p1])
    }

}