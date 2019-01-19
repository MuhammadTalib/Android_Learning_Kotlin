package com.example.muhammadtalib.sendimage

import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.content.res.Resources
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Environment
import android.widget.ImageView
import android.support.v4.content.FileProvider
import android.os.Environment.DIRECTORY_PICTURES
import android.widget.Toast
import android.provider.MediaStore
import android.media.MediaScannerConnection
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.view.View
import android.widget.Button
import java.io.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class MainActivity : AppCompatActivity() {

    val RESULT_LOAD_IMAGE=1;
    lateinit var  selectedImage:Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        share_button.setOnClickListener{
            val i = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(i, RESULT_LOAD_IMAGE)
        }
        share_drawable_button.setOnClickListener{
            selectedImage=Uri.parse(image.resources.toString())
           ShareGallery()
        }
    }


     fun ShareGallery() {

        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
         shareIntent.setType("image/jpeg")
        shareIntent.putExtra(Intent.EXTRA_STREAM, selectedImage)
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Sharing My Application Image")
        startActivity(Intent.createChooser(shareIntent, "Share from"))
    }
    fun ShareDrawable()
    {

        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.setType("image/jpeg")
        shareIntent.putExtra(Intent.EXTRA_STREAM, selectedImage)
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Sharing My Application Image")
        startActivity(Intent.createChooser(shareIntent, "Share from"))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK
            && null != data) {
        selectedImage = data.getData();
            ShareGallery()
       //image.setImageURI(selectedImage)
    }
    }
}




