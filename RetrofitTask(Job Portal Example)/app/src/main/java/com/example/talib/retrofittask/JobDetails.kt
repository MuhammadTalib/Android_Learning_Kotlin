package com.example.talib.retrofittask

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.CircularProgressDrawable
import android.text.Html
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_job_details.*

class JobDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_details)

        Glide.with(this@JobDetails).applyDefaultRequestOptions(RequestOptions().apply {
            placeholder(CircularProgressDrawable(this@JobDetails).apply {
                strokeWidth = 2f
                centerRadius = 50f
                start()
            })
        }).load(JobActivity.ParticularJob.company_logo).into(job_imagee)

        Job_CompanyName.text=JobActivity.ParticularJob.company
        Job_Title.text= JobActivity.ParticularJob.title
        Job_Type.text=JobActivity.ParticularJob.type

        var value2:String = JobActivity.ParticularJob.description
        var SiteLink2:TextView= findViewById(R.id.description);
        SiteLink2.setText(Html.fromHtml(value2));
        SiteLink2.setMovementMethod(LinkMovementMethod.getInstance())

        var value1:String = JobActivity.ParticularJob.how_to_apply
        var SiteLink1:TextView= findViewById(R.id.ClickHere);
        SiteLink1.setText(Html.fromHtml(value1));
        SiteLink1.setMovementMethod(LinkMovementMethod.getInstance())

        var value:String = "<html> <a href=\"${JobActivity.ParticularJob.company_url}\">${JobActivity.ParticularJob.company_url}</a> </html>";
        var SiteLink:TextView= findViewById(R.id.Job_Link);
        SiteLink.setText(Html.fromHtml(value));
        SiteLink.setMovementMethod(LinkMovementMethod.getInstance())
    }
}
