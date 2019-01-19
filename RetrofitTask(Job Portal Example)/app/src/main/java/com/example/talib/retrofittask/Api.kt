package com.example.talib.retrofittask

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api
{
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jobs.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().addNetworkInterceptor(StethoInterceptor()).build())
        .build()

    val service = retrofit.create(JobApi::class.java)
}