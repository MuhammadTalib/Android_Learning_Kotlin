package com.example.muhammadtalib.listview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_list_view.*


class list_view : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        val users = arrayListOf("arbaz", "ishaq", "usman", "talha", "usama","arbaz", "ishaq", "usman", "talha", "usama","arbaz", "ishaq", "usman", "talha", "usama")

//        val listAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,users)
//        val listAdapter = CustomAdapter(this,users)


        val userList = ArrayList<UserModal>()

        userList.add(UserModal("ishaq",24,R.drawable.ic_launcher_background))
        userList.add(UserModal("arbaz",24,R.drawable.ic_launcher_background))
        userList.add(UserModal("usman",34,R.drawable.ic_launcher_background))
        userList.add(UserModal("rameez",54,R.drawable.ic_launcher_background))
        userList.add(UserModal("ishaq",64,R.drawable.ic_launcher_background))


        val listAdapter = UserCustomAdapter(this,userList)
        mylist.adapter = listAdapter
        mylist.setOnItemClickListener { parent, view, position, id ->
            T(users[position])
        }

    }
}