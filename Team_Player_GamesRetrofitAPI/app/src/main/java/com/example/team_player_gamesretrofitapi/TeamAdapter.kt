package com.example.team_player_gamesretrofitapi

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup


class TeamAdapter(val data:ArrayList<TeamData>) : RecyclerView.Adapter<TeamViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TeamViewHolder = TeamViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.team_item,p0,false))
    override fun getItemCount(): Int = data.size
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(p0: TeamViewHolder, p1: Int) {
        p0.city.text=data[p1].city
        p0.conference.text=data[p1].conference
        p0.name.text=data[p1].name
        p0.devision.text=data[p1].division
        p0.fullname.text=data[p1].full_name
        p0.countid.text=(p1+1).toString()+"->"
    }

}