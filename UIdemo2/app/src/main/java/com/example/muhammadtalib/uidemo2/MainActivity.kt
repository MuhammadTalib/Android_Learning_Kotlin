package com.example.muhammadtalib.uidemo2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity(),View.OnClickListener {

    companion object {
        val MY_NAME_KEY = "myName"
        val MY_AGE_KEY = "myAge"
    }
    lateinit var img:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnCngActv : Button = findViewById(R.id.buttonChangeActivity)

        img=findViewById(R.id.myImg)
        btnCngActv.setOnClickListener{v ->
            val i = Intent(this,SecondActivity::class.java)
            i.putExtra(MY_NAME_KEY,"Hello World From Views Activity")
            i.putExtra(MY_AGE_KEY,19)
            startActivity(i)
        }

        val button1:Button=findViewById(R.id.button1)
        button1.setOnClickListener(this)
    }



    var i:Int=0
    override fun onClick(v: View?) {

       if(i%2==0)
       {
           Toast.makeText(this,"Btn 1",Toast.LENGTH_SHORT).show()
           img.setImageResource(android.R.drawable.ic_delete)
           i++;
       }
       else
       {
           Toast.makeText(this,"Btn 1",Toast.LENGTH_SHORT).show()
           img.setImageResource(android.R.drawable.ic_btn_speak_now)
           i++;
       }
     }
}
