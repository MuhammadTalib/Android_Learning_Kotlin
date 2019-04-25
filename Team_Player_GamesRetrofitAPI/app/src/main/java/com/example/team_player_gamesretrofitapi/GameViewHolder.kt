package com.example.team_player_gamesretrofitapi

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class GameViewHolder(v: View) : RecyclerView.ViewHolder(v){
    var HomeTeam: TextView = itemView.findViewById(R.id.name)
    var HomeTeamScore: TextView = itemView.findViewById(R.id.fullname)
    var VisitorTeam: TextView = itemView.findViewById(R.id.city)
    var VisitorTeamScore: TextView = itemView.findViewById(R.id.devision)
    var Season: TextView = itemView.findViewById(R.id.season)
    var countid: TextView =itemView.findViewById(R.id.countid)
}