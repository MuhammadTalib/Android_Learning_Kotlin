package com.example.backgroundservices

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //startService(Intent(this,MyTestService::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("hahaha","destroyed")
        startService(Intent(this,MyTestService::class.java))
    }
}
