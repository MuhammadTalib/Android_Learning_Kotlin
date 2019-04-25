package com.example.team_player_gamesretrofitapi

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity(),retrofit2.Callback<TeamObject> {

    companion object {
        var mTeamData:ArrayList<TeamData>?=null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hitteam.setOnClickListener {



        }

    }
    override fun onFailure(call: Call<TeamObject>, t: Throwable) {
         }

    override fun onResponse(call: Call<TeamObject>, response: Response<TeamObject>) {
        Log.e("hahaha","onResponse")
        val myTeam = response.body()
        mTeamData=myTeam?.data
        startActivity(Intent(this,TeamList::class.java))
    }
}
