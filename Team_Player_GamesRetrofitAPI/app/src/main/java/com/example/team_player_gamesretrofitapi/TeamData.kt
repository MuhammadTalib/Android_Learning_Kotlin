package com.example.team_player_gamesretrofitapi

import java.io.Serializable

class TeamData{
    var id:String="1"
    var city:String	="Boston"
    var conference:String="East"
    var division:String="Southeast"
    var full_name:String="Atlanta Hawks"
    var name:String="Hawks"
}

class TeamObject:Serializable{

    val data:ArrayList<TeamData>?=null
}
