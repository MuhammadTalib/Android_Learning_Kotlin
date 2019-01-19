package com.example.muhammadtalib.uidemo1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onUniClick(view:View)
    {
        Toast.makeText(this,"Hello World",Toast.LENGTH_SHORT).show();
    }
}
