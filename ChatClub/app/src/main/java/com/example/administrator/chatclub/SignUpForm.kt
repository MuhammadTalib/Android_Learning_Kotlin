package com.example.administrator.chatclub

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main_page.*
import kotlinx.android.synthetic.main.activity_sign_up_form.*

class SignUpForm : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_form)

        signupsignup.setOnClickListener()
        {
            var temp = UserAccount(signupemailinput.text.toString(), signupnameinput.text.toString(), signuppasswordinput.text.toString())
            MainPage.AccountData.add(temp)
            Log.e("hahaha",temp.Username)
            val intent= Intent(this,MainPage::class.java)
            startActivity(intent)
        }

    }
}
