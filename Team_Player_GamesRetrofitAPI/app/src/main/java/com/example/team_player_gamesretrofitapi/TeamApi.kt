package com.example.team_player_gamesretrofitapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TeamApi{

    @GET("/api/v1/teams")
    fun getTeam(): Call<TeamObject>

    @GET("/api/v1/players")
    fun getPlayers(): Call<PlayersObject>

    @GET("/api/v1/games")
    fun getGames(): Call<GamesObject>

    @GET("/api/v1/games")
    fun getOneGamePage(@Query("page") page:String): Call<GamesObject>

    @GET("/api/v1/teams")
    fun getOneTeamPage(@Query("page") page:String): Call<TeamObject>

    @GET("/api/v1/players")
    fun getOnePlayerPage(@Query("page") page:String): Call<PlayersObject>


}