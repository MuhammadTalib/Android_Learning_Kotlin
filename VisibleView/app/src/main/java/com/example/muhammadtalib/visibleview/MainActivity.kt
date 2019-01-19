package com.example.muhammadtalib.visibleview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{
            notvisible.visibility=View.GONE
        }
        button1.setOnClickListener{
            notvisible.visibility=View.VISIBLE
        }
    }
}
