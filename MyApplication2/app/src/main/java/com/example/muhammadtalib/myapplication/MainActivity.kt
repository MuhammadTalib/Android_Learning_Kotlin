package com.example.muhammadtalib.myapplication

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.IOException

class MainActivity : AppCompatActivity() {

    lateinit var photoPath:String
    val REQUEST_TAKE_PHOTO=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        takepicture.setOnClickListener{
            takePicture()
        }
    }
    fun takePicture()
    {
        val intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(intent.resolveActivity(packageManager)!=null)
        {
            var photofile: File?=null;
            try{
                photofile=createImageFile()
            }catch (e: IOException){}
            if(photofile!=null)
            {
                val photoUri: Uri?= FileProvider.getUriForFile(this,"$packageName.fileprovider",
                        photofile
                )

                intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri)
                startActivityForResult(intent,REQUEST_TAKE_PHOTO)
            }

        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==REQUEST_TAKE_PHOTO&& resultCode== Activity.RESULT_OK)
        {
            myImageView.rotation=90f
            myImageView.setImageURI(Uri.parse(photoPath))

        }
    }
    fun createImageFile(): File?
    {
        val fileName="MyPicture"
        val storagedir=getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image= File.createTempFile(fileName,".jpg",storagedir)
        photoPath=image.absolutePath

        return image
    }
}
