package com.example.team_player_gamesretrofitapi


import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.balldontlie.io")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(TeamApi::class.java)
}