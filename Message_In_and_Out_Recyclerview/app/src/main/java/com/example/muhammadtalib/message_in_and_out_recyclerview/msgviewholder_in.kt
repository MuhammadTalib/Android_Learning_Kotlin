package com.example.muhammadtalib.message_in_and_out_recyclerview

import android.view.View
import android.widget.TextView

class MsgViewHolderIn(myItem: View) : MsgViewHolder(myItem){
    val userNameTextView: TextView = itemView.findViewById(R.id.userNameTv)

    override fun bindItem(msgItem: MsgItem) {
        super.bindItem(msgItem)
        userNameTextView.text = "Hello world Arbaz"
    }
}