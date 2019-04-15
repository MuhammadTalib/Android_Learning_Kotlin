package com.example.talib.galleryimageinput

import android.app.Activity
import android.content.Intent
import android.media.Image
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val REQ_CODE = 1
    var selectMedia: Uri? = null
    //   var messageList:ArrayList<Message> = ArrayList()
    var senderId = 0
    var check:Boolean = false
    var myBitmapImg:Any=0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gallery_input.setOnClickListener{

            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/* video/*"
            startActivityForResult(intent, 1)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(data!=null){
            if(requestCode==REQ_CODE && resultCode == Activity.RESULT_OK){
                val photoUri = data.data
                if(photoUri.toString().contains("image")){
                    myBitmapImg=photoUri as Any
                    image.visibility=View.VISIBLE
                    image.setImageURI(myBitmapImg as Uri)
                }
                else if(photoUri.toString().contains("video")){
                    selectMedia = photoUri
                    Toast.makeText(this,"video Selected!!", Toast.LENGTH_SHORT+2).show()
                    video.visibility = View.VISIBLE
                    video.setVideoURI(photoUri)
                    video.start();

                }

            }
        }else{
            Toast.makeText(this,"No image Selected!!", Toast.LENGTH_SHORT+2).show()
        }

    }
}
