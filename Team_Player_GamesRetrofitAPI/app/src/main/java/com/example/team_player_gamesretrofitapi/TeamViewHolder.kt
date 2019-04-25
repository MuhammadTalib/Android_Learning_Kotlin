package com.example.team_player_gamesretrofitapi

import android.support.v4.widget.CircularProgressDrawable
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class TeamViewHolder(v: View) : RecyclerView.ViewHolder(v){
    var name: TextView = itemView.findViewById(R.id.name)
    var fullname: TextView = itemView.findViewById(R.id.fullname)
    var city: TextView = itemView.findViewById(R.id.city)
    var devision: TextView = itemView.findViewById(R.id.devision)
    var conference: TextView = itemView.findViewById(R.id.conference)
    var countid:TextView=itemView.findViewById(R.id.countid)
}