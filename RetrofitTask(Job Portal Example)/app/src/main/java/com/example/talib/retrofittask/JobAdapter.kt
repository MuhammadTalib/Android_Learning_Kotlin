package com.example.talib.retrofittask

import android.content.Intent
import android.support.v4.widget.CircularProgressDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class JobAdapter(val data:ArrayList<Job>,val OnItemClick:(Int)->Unit) : RecyclerView.Adapter<JobAdapter.JobViewHolder>()
{

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): JobViewHolder = JobViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.job_item_view,p0,false))
    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(p0: JobViewHolder, p1: Int)
    {
        p0.bindBook(data[p1])
        p0.itemView.setOnClickListener {
            OnItemClick(p1)
        }
    }

    class JobViewHolder(v: View) : RecyclerView.ViewHolder(v)
    {
        val JobTitle: TextView = itemView.findViewById(R.id.view_title)
        var JobImage: ImageView= itemView.findViewById(R.id.view_image)
        val JobType:TextView=itemView.findViewById(R.id.view_type)
        val Job_PostingTime:TextView=itemView.findViewById(R.id.view_created_at)
        val Job_CompanyName:TextView=itemView.findViewById(R.id.view_company_name)

        fun bindBook(job:Job)
        {

            JobTitle.text=job.title
            JobType.text=job.type
            Job_CompanyName.text=job.company
            Job_PostingTime.text="${job.created_at[8]} ${job.created_at[9]} "

            if(job.company_logo==null)
            {
                JobImage.setImageResource(R.drawable.no_image_available)
            }
            else
            {
                Glide.with(itemView.context).applyDefaultRequestOptions(RequestOptions().apply {
                placeholder(CircularProgressDrawable(itemView.context).apply {
                    strokeWidth = 2f
                    centerRadius = 50f
                    start()
                })
                }).load(job.company_logo).into(JobImage)
            }

        }
    }

}