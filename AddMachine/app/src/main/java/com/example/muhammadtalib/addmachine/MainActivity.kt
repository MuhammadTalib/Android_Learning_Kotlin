package com.example.muhammadtalib.addmachine

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity(),View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val FirstNumber:EditText=findViewById(R.id.FirstNumber)
        val SecondNumber:EditText=findViewById(R.id.SecondNumber)

    }

    override fun onClick(v: View?)
    {

    }
}
