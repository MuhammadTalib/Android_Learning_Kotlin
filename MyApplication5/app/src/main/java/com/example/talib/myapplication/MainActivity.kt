package com.example.talib.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var i:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
            if(i==0)
            {
                i=1
                text.text="Text Changed"
            }
            else
            {
                i=0
                text.text="Again Changed"
            }
        }
    }
}
