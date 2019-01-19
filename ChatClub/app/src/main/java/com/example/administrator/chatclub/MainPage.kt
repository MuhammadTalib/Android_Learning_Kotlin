package com.example.administrator.chatclub

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main_page.*

class MainPage : AppCompatActivity() {

    companion object
    {
        var MyAccount=UserAccount("","","")
        var AccountData = ArrayList<UserAccount>()
    }
    var found:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        AccountData.add(UserAccount("Talib","Talib","Talib"))

        login.setOnClickListener() {
            if(!checkbox.isChecked())
            {
                checkboxerror.text="*You have to agree with terms and conditions"
            }
            else {
                checkboxerror.text=""
                var temp = UserAccount(emailinput.text.toString(), nameinput.text.toString(), passwordinput.text.toString())
                for (i in AccountData) {
                    if (i.Email==temp.Email && i.Username==temp.Username && i.password==temp.password) {
                        found = 1;
                        MyAccount=i
                    }
                }
                if (found == 0) {
                    error.text ="**User Name or Password Incorrect"
                }
                else{
                    val intent= Intent(this,ChatList::class.java)
                    startActivity(intent)
                }
            }

        }
        signup.setOnClickListener() {


            val intent= Intent(this,SignUpForm::class.java)
            startActivityForResult(intent,1)
        }

    }
    fun google(view: View)
    {

    }
    fun googleplus(view: View)
    {

    }
    fun facebook(view:View)
    {

    }
}
