package com.example.talib.sendmessagetosim

import android.Manifest
import android.app.PendingIntent.getActivity
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.telephony.SmsManager
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sendbtn.setOnClickListener {
            if (isSMSPermissionGranted()) {
                send("03232452884", text1.text.toString());
            }
        }
    }

    fun send(phoneNo: String, msg: String) {


        try
        {
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNo, null, msg, null, null)
        }
        catch (e: Exception)
        {
            e.printStackTrace()
        }


    }

    fun isSMSPermissionGranted(): Boolean {

        if (Build.VERSION.SDK_INT >= 23)
        {
            if (checkSelfPermission(android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)
            {
                return true
            }
            else
            {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), 0)
                return false
            }
        }
        else
        {
            return true
        }
    }


}
