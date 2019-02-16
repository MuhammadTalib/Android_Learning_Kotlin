package com.example.administrator.chatclub

import android.content.Context
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth

class MessageListAdapter(val tHIS: Context, val msgs:ArrayList<Message>, val current_user_id:String) : RecyclerView.Adapter<MessageViewHolder>() {
    val MSG_IN = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        if(viewType == MSG_IN){
            return MsgViewHolderIn(LayoutInflater.from(parent.context).inflate(R.layout.msg_item_in,parent,false))
        }else {
            return MsgViewHolderOut(LayoutInflater.from(parent.context).inflate(R.layout.msg_item_out,parent,false))
        }
    }

    override fun getItemCount(): Int = msgs.size

    override fun onBindViewHolder(viewHolder: MessageViewHolder, position: Int)
    {
        viewHolder.bindItem(msgs[position])
        if(msgs[position].image!=null)
        {
            Log.e("hahaha","not null")
            viewHolder.msgMsgImageView.visibility= View.VISIBLE
            Glide.with(tHIS).load(msgs[position].image).into(viewHolder.msgMsgImageView)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(current_user_id==msgs[position].userId)
        {
            return 1
        }
        else
        {   return 0
        }
    }
    fun add(msg:Message)
    {Log.e("hahaha",msg.messageText)
        msgs.add(msg)
        Log.e("hahaha","add ${msg.messageText}")
        notifyItemInserted(msgs.size-1)
        Log.e("hahaha","notified")
    }
}