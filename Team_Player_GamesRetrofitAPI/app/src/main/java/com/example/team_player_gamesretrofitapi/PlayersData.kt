package com.example.team_player_gamesretrofitapi

import java.io.Serializable

class PlayersData {
    var id	=	"5"
    var first_name	=	"DeVaughn"
    var height_feet	=	"null"
    var height_inches	=	"null"
    var last_name	=	"Akoon-Purcell"
    var position	="	G-F"
}

class PlayersObject: Serializable {

    val data:ArrayList<PlayersData>?=null
}