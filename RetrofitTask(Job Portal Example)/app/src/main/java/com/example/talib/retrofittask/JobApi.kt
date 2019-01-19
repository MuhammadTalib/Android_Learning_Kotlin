package com.example.talib.retrofittask


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface JobApi{

    @GET("positions.json?")
    fun getJobs(@Query("description") description:String,
                @Query("location") location:String): Call<ArrayList<Job>>


}