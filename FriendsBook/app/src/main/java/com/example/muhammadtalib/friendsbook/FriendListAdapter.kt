package com.example.muhammadtalib.friendsbook

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class FriendListAdapter ( val data:ArrayList<Friend>,
                          val onItemClick:(Int)->Unit): RecyclerView.Adapter<FriendViewHolder>() {

    override fun getItemCount(): Int =data.size


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
            val itemView= LayoutInflater.from(parent.context).inflate(R.layout.friend_list_item_view,parent,false)
            return FriendViewHolder(itemView)
        }
        override fun onBindViewHolder(p0: FriendViewHolder, p1: Int) {
            p0.myImage.setImageResource(data[p1].Image)
            p0.myText.text=data[p1].name
            p0.myStatus.text=data[p1].status
            p0.itemView.setOnClickListener {
                onItemClick(p1)
            }
        }
    }
