package com.example.team_player_gamesretrofitapi

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class PlayerAdapter(val data:ArrayList<PlayersData>) : RecyclerView.Adapter<PlayerViewHolder>(){

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PlayerViewHolder = PlayerViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.player_item,p0,false))
        override fun getItemCount(): Int = data.size
        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(p0: PlayerViewHolder, p1: Int) {
            p0.FirstName.text=data[p1].first_name
            p0.LastName.text=data[p1].last_name
            p0.Height.text=data[p1].height_feet
            p0.Position.text=data[p1].position
            p0.countid.text=(p1+1).toString()+"->"
        }
}