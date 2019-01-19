package com.example.talib.retrofit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity(), retrofit2.Callback<VolumesResponse> {

    var booksData:ArrayList<VolumesResponse.VolumeItem> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        booksList.adapter = BooksAdapter(booksData)
        booksList.layoutManager = LinearLayoutManager(this)

        apiBtn.setOnClickListener {
            booksData.clear()
            Api().service.getVolumes(bookname.text.toString()).enqueue(this)
//            Api().service.getVol("android").enqueue(this)


        }
    }

    override fun onFailure(call: Call<VolumesResponse>, t: Throwable) {

    }

    override fun onResponse(call: Call<VolumesResponse>, response: Response<VolumesResponse>) {
        val myVolumes = response.body()
        myVolumes?.also {vol ->
            vol.items?.also {
                Toast.makeText(this@MainActivity,"OK",Toast.LENGTH_SHORT).show()
                booksData.addAll(it.toList())
                booksList.adapter?.notifyDataSetChanged()
            }
        }
    }
}