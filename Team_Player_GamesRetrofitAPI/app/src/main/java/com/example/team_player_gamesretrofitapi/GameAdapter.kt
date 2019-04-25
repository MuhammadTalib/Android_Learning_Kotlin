package com.example.team_player_gamesretrofitapi

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class GameAdapter(val data:ArrayList<GamesData>) : RecyclerView.Adapter<GameViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): GameViewHolder = GameViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.games_item,p0,false))
    override fun getItemCount(): Int = data.size
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(p0: GameViewHolder, p1: Int) {
        p0.HomeTeam.text=data[p1].home_team?.full_name
        p0.HomeTeamScore.text=data[p1].home_team_score.toString()
        p0.VisitorTeam.text=data[p1].visitor_team?.full_name
        p0.VisitorTeamScore.text=data[p1].visitor_team_score.toString()
        p0.Season.text=data[p1].season.toString()
        p0.countid.text=(p1+1).toString()+"->"
    }

}