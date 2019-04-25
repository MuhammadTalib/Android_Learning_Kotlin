package com.example.team_player_gamesretrofitapi


import java.io.Serializable

class GamesData {
    var id	=	47179
    var home_team:TeamData?=null
    var home_team_score	=	126
    var period	=	4
    var postseason	=	false
    var season	=	2018
    var status	=	"Final"
    var visitor_team:TeamData?	=	null
    var visitor_team_score	=	94
}

class GamesObject: Serializable {

    val data:ArrayList<GamesData>?=null
}