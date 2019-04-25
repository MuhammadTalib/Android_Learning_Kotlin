package com.example.team_player_gamesretrofitapi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_team_list.*

class TeamList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_list)

       // teamlist.adapter=TeamAdapter(MainActivity.mTeamData!!)
       // teamlist.layoutManager=LinearLayoutManager(this)

    }
}
