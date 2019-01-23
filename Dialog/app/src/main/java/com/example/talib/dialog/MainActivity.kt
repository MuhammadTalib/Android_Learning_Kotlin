package com.example.talib.dialog

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        show.setOnClickListener{
           val dialog = CustomDialog(this)
            dialog.setCancelable(true)
            dialog.show()

            dialog.setOnDismissListener {

            }
            /* startActivity(Intent(this,Empty::class.java))
            overridePendingTransition(R.anim.slide_digonal,0)
*/

        }


    }
}
