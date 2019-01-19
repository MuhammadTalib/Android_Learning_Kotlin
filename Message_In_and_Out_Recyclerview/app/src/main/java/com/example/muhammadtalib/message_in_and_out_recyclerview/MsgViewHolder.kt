package com.example.muhammadtalib.message_in_and_out_recyclerview

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

abstract class MsgViewHolder(myItem: View) : RecyclerView.ViewHolder(myItem) {
    val msgMsgTextView: TextView = itemView.findViewById(R.id.myMsgTv)

    open fun bindItem(msgItem: MsgItem){
        msgMsgTextView.text = msgItem.msgText
    }
}