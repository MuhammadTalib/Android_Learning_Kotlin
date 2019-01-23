package com.example.talib.dialog

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class Empty : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_up,0)
    }
}
