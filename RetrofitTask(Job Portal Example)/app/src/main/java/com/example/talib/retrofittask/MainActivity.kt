package com.example.talib.retrofittask

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    companion object
    {
        var titlee:String=""
        var city:String=""
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        search.setOnClickListener {

            titlee=jobtitle.text.toString()
            city=cityname.text.toString()

            var Intent= Intent(this,JobActivity::class.java)
            startActivity(Intent)


        }
    }



}


