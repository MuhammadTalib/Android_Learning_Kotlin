package com.example.muhammadtalib.listview

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)


        val users = arrayListOf("arbaz", "ishaq", "usman", "talha", "usama","arbaz", "ishaq", "usman", "talha", "usama","arbaz", "ishaq", "usman", "talha", "usama")

//        val listAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,users)
//        val listAdapter = CustomAdapter(this,users)


        val userList = ArrayList<UserModal>()

        userList.add(UserModal("ishaq",24,R.drawable.ic_launcher_background))
        userList.add(UserModal("arbaz",24,R.drawable.ic_launcher_background))
        userList.add(UserModal("usman",34,R.drawable.ic_launcher_background))
        userList.add(UserModal("rameez",54,R.drawable.ic_launcher_background))
        userList.add(UserModal("ishaq",64,R.drawable.ic_launcher_background))

        var spinnerAdapter: CustomDropdownAdapter = CustomDropdownAdapter(this,userList)
        var spinner: Spinner = findViewById(R.id.spinner) as Spinner
        spinner?.adapter = spinnerAdapter


    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {
            T("Nothing")
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            T(users[position])
        }

    }


    add_user.setOnClickListener {

        val user = user_data.text.toString()
        users.add(user)
        user_data.setText("")

        T("user added")


    }


}


}

fun Context.T(mess: String) {
    Toast.makeText(this, mess, Toast.LENGTH_SHORT).show()
}

