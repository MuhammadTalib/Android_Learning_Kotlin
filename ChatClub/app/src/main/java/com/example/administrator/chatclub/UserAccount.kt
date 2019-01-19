package com.example.administrator.chatclub

class UserAccount(var Email:String,var Username:String,var password:String)
{

    var Image:Int=R.drawable.ic_person_pin_circle_black_24dp
    var lastmessage:String="Say Hi to you new friend ${this.Username}"
}