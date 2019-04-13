package com.example.imageprocessing


import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*





class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var b:Bitmap?=null
        val rgbs = Array(b.width * b.height) {
            val x = it % b.height
            val y = it / b.height

            val pixel = b.getPixel(x, y)
            val red = (pixel and 0x00FF0000 shr 16) / 255f
            val green = (pixel and 0x0000FF00 shr 8) / 255f
            val blue = (pixel and 0x000000FF) / 255f

            RGB(red, green, blue)
        }

        data class RGB(val r: Float, val g: Float, val b: Float)
    }
}
