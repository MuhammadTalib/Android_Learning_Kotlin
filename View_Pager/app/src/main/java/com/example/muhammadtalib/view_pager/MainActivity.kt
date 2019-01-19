package com.example.muhammadtalib.view_pager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myPager.adapter = MypagerAdapter(supportFragmentManager, arrayListOf(
                Frag1(),
                Frag2()
        ))
        val tabLayout = findViewById(R.id.myTabs) as TabLayout
        tabLayout.setupWithViewPager(myPager)
    }

}


