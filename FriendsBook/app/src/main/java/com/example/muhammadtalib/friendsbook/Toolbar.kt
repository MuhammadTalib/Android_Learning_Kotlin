package com.example.muhammadtalib.friendsbook

import android.content.Context
import android.net.Uri
import android.os.Bundle

import android.content.Intent
import android.os.Messenger
import android.support.v4.app.Fragment

import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.Toast
import com.example.muhammadtalib.friendsbook.R.id.container

class Toolbar : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_toolbar, container, false)
    }
    fun MessengerClick(view: View)
    {

    }

}
