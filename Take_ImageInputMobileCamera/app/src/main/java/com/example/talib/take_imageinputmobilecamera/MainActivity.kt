package com.example.talib.take_imageinputmobilecamera


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.AlarmClock
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.FileProvider
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.IOException
import java.net.URI
import java.text.SimpleDateFormat
import java.util.*

/*
class MainActivity(var photoFile: File? = null) : AppCompatActivity() {

    val REQUEST_TAKE_PHOTO = 1
    var mCurrentPhotoPath:String? = null

    private fun dispatchTakePictureIntent()
    {
        Log.e("pic","start func")
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            Log.e("pic","start intent")
            takePictureIntent.resolveActivity(packageManager)?.also {
                Log.e("pic","also")
                photoFile = try
                {
                    Log.e("pic","try")
                    createImageFile()
                }

                catch (ex: IOException)
                {
                    Log.e("pic","catch")
                    null
                }
                Log.e("pic","created")

                Log.e("pic","catch passed")
                photoFile?.also {
                    Log.e("pic","in pf also")
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this,
                        "com.example.talib.take_imageinputmobilecamera.fileprovider",
                        it
                    )
                    Log.e("pic","uri created")
                    Log.e("pic","${photoFile?.exists()}")
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
                }
            }
        }
    }

    private fun createImageFile(): File
    {
        Log.e("pic","create image")
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        Log.e("pic","set date")
        val storageDir: File = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        Log.e("pic","set storage")
        val f = File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        )
        Log.e("pic","file created")
        mCurrentPhotoPath = f.absolutePath
        Log.e("pic","before return")
        return f
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        capBtn.setOnClickListener {
            dispatchTakePictureIntent()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_TAKE_PHOTO){
            photoFile?.also {
                val myBitmap = BitmapFactory.decodeFile(it.absolutePath)
                myImg.setImageBitmap(myBitmap)
            }

        }
    }


}*/

class MainActivity : AppCompatActivity() {

    val REQUEST_TAKE_PHOTO = 1
    var mCurrentPhotoPath:String? = null
    var photoFile:File? = null

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(packageManager)?.also {
                // Create the File where the photo should go
                photoFile = try {
                    createImageFile()
                } catch (ex: IOException) {
                    // Error occurred while creating the File
                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this,
                        "$packageName.fileprovider",
                        it
                    )
                    Log.e("FILE_EXISTS","${photoFile?.exists()}")
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
                }
            }
        }
    }

    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val f = File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        )
        mCurrentPhotoPath = f.absolutePath
        return f
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        capBtn.setOnClickListener {
            dispatchTakePictureIntent()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_TAKE_PHOTO){
            photoFile?.also {
                val myBitmap = BitmapFactory.decodeFile(it.absolutePath)
                myImg.setImageBitmap(myBitmap)
            }

        }
    }


}