package com.example.talib.retrofittask

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.CircularProgressDrawable
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_job.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class JobActivity : AppCompatActivity(), retrofit2.Callback<ArrayList<Job>> {

    companion object {
        var ParticularJob:Job=Job()
    }
    var JobData:ArrayList<Job> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job)


        JobData.clear()
        Api().service.getJobs(MainActivity.titlee,MainActivity.city).enqueue(this)

        joblist.adapter = JobAdapter(JobData,::onItemClick)
        joblist.layoutManager = LinearLayoutManager(this)


    }
    override fun onFailure(call: Call<ArrayList<Job>>, t: Throwable) {
    }

    override fun onResponse(call: Call<ArrayList<Job>>, response: Response<ArrayList<Job>>)
    {
        val myJob = response.body()
        myJob?.also {vol ->
            vol.also {

                JobData.addAll(it.toList())
                if(JobData.isEmpty())
                {
                    text1.visibility=View.VISIBLE
                }
                joblist.adapter?.notifyDataSetChanged()
            }
        }
    }
    fun onItemClick(position:Int)
    {
        ParticularJob=JobData[position]
        var i=Intent(this,JobDetails::class.java)
        startActivity(i)
    }
}
