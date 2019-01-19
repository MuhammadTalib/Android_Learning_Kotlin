package com.example.muhammadtalib.fragments

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

class BlankFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.activity_blank_fragment, container, false)
    }

    fun toast()
    {
        Toast.makeText(context,"hello world",Toast.LENGTH_SHORT).show()
    }

}