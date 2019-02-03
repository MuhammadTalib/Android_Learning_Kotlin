package com.example.administrator.chatclub

import android.content.Context
import android.view.View
import android.widget.Toast

fun Context.t(m:String) = Toast.makeText(this,m,Toast.LENGTH_SHORT).show()

fun Context.toast(msg:String?){
    Toast.makeText(this,msg ?: "",Toast.LENGTH_LONG).show()
}

fun View.show(){
    visibility = View.VISIBLE
}
fun View.hide(){
    visibility = View.GONE
}