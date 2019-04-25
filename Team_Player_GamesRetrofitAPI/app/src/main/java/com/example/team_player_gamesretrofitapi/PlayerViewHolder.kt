package com.example.team_player_gamesretrofitapi

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class PlayerViewHolder(v: View) : RecyclerView.ViewHolder(v){
    var FirstName: TextView = itemView.findViewById(R.id.name)
    var LastName: TextView = itemView.findViewById(R.id.fullname)
    var Position: TextView = itemView.findViewById(R.id.city)
    var Height: TextView = itemView.findViewById(R.id.devision)
    var countid:TextView=itemView.findViewById(R.id.countid)
}