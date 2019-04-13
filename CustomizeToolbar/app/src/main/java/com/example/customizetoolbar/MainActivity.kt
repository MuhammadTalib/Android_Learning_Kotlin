package com.example.customizetoolbar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.app.ActionBar
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.toolbar.*
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var mToolbar: Toolbar=findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(mToolbar)
        img.setOnClickListener {
            Log.e("hahaha","aaaa")
            Toast.makeText(this,"Image",Toast.LENGTH_SHORT)
        }
    }

}
