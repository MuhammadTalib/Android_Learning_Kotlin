package com.example.muhammadtalib.friendsbook

import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.content.Intent.CATEGORY_APP_GALLERY
import android.media.browse.MediaBrowser
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Gallery
import android.widget.Toast

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

    }
    fun poster(view: View)
    {
        val i = Intent(CATEGORY_APP_GALLERY)
        startActivityForResult(i,2);
    }
}
