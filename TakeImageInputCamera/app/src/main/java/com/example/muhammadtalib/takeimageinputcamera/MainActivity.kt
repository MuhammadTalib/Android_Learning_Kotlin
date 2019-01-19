package com.example.muhammadtalib.takeimageinputcamera

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.IOException
import android.graphics.drawable.BitmapDrawable



class MainActivity : AppCompatActivity() {

    lateinit var photoPath:String
    val REQUEST_TAKE_PHOTO=1
    lateinit var bitmap:Bitmap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        takepicture.setOnClickListener{
            takePicture()
        }
        replacepicture.setOnClickListener{
            myImageVieww.setImageBitmap(bitmap)
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
            val myBitmap = BitmapFactory.decodeFile(photoPath)
            myImageView.setImageBitmap(myBitmap)
            bitmap=myBitmap

        }
    }
    fun createImageFile(): File?
    {
        val fileName="MyPicture"

        val storagedir=getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image= File.createTempFile(fileName,".jpeg",storagedir)
        photoPath=image.absolutePath

        return image
    }

}
