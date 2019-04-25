package com.example.userlocation

import android.Manifest
import android.annotation.TargetApi
import android.app.Service
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LocationListener {

    companion object {

        var LocationLatLang: LatLng?=null
        lateinit var userlist:ArrayList<users>
    }

    val TAG = "GPS"
    private val ALL_PERMISSIONS_RESULT = 101
    private val MIN_DISTANCE_CHANGE_FOR_UPDATES: Long = 10
    private val MIN_TIME_BW_UPDATES = (1000 * 6).toLong()


    var locationManager: LocationManager? = null
    var loc: Location? = null
    var permissions = java.util.ArrayList<String>()
    lateinit var permissionsToRequest: java.util.ArrayList<String>
    var permissionsRejected = java.util.ArrayList<String>()
    var isGPS = false
    var isNetwork = false
    var canGetLocation = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userlist= arrayListOf()
       // userlist[0].findaddress(this@MainActivity)
        if(LocationLatLang!=null){
            latitude.visibility= View.VISIBLE
            latitudenum.visibility= View.VISIBLE
            longitude.visibility= View.VISIBLE
            longitudenum.visibility= View.VISIBLE

            latitudenum.text=LocationLatLang?.latitude.toString()
            longitudenum.text=LocationLatLang?.longitude.toString()
        }

        open_map.setOnClickListener{
            if(LocationLatLang==null){
                locationManager = getSystemService(Service.LOCATION_SERVICE) as LocationManager
                isGPS = locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
                isNetwork = locationManager!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION)
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION)
                permissionsToRequest = findUnAskedPermissions(permissions)

                if (!isGPS && !isNetwork) {
                    Log.e(TAG, "Connection off")
                    showSettingsAlert()
                    getLastLocation()
                } else {
                    Log.e(TAG, "Connection on")
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (permissionsToRequest.size > 0) {
                            requestPermissions(
                                permissionsToRequest.toTypedArray(),
                                ALL_PERMISSIONS_RESULT
                            )
                            Log.e(TAG, "Permission requests")
                            canGetLocation = false
                        }
                    }
                    getLocation()

                }
            }
            startActivity(Intent(this, GoogleMap::class.java))
        }

        register.setOnClickListener {
            val newuser=users().apply {
            this.Name = name.text.toString()
            this.PhNumber=number.text.toString()
            this.location= LocationLatLang

            }
            newuser.findaddress(this@MainActivity)
            userlist.add(newuser)
            Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()
        }
        show_list.setOnClickListener {
            Log.e("hahaha","button clicked")
            Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show()
            for(user in userlist!!)
            {
                Log.e("hahaha",user.Name)
            }
            val i=Intent(this,userList::class.java)
            startActivity(i)
        }
    }
    private fun updateUI(loc: Location) {

        Log.e(TAG,loc.latitude.toString()+" "+loc.longitude.toString())
        LocationLatLang= LatLng(loc.latitude,loc.longitude)
        Log.e("hahaha",loc.latitude.toString()+"   "+loc.longitude.toString())

        latitude.visibility= View.VISIBLE
        latitudenum.visibility= View.VISIBLE
        longitude.visibility= View.VISIBLE
        longitudenum.visibility= View.VISIBLE

        latitudenum.text=loc.latitude.toString()
        longitudenum.text=loc.longitude.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (locationManager != null) {
            locationManager?.removeUpdates(this)
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("hahahah","on Restart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("hahahah","on Resume")
    }



    fun showSettingsAlert() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("GPS is not Enabled!")
        alertDialog.setMessage("Do you want to turn on GPS?")
        alertDialog.setPositiveButton("Yes") { dialog, _ ->
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        }

        alertDialog.setNegativeButton(
            "No"
        ) { dialog, which -> dialog.cancel() }

        alertDialog.show()
    }

    private fun showMessageOKCancel(okListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(this@MainActivity)
            .setMessage("These permissions are mandatory for the application Please allow access")
            .setPositiveButton("OK", okListener)
            .setNegativeButton("Cancel", null)
            .create()
            .show()
    }

    @TargetApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            ALL_PERMISSIONS_RESULT -> {
                Log.e(TAG, "onRequestPermissionsResult")
                for (perms in permissionsToRequest) {
                    if (hasPermission(perms)) {
                        permissionsRejected.add(perms)
                    }
                }
                if (permissionsRejected.size > 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(permissionsRejected[0])) {
                            showMessageOKCancel(
                                DialogInterface.OnClickListener { dialog, which ->
                                    requestPermissions(
                                        permissionsRejected.toTypedArray(),
                                        ALL_PERMISSIONS_RESULT
                                    )
                                })
                            return
                        }
                    }
                } else {
                    Log.e(TAG, "No rejected permissions.")
                    canGetLocation = true
                    getLocation()
                }
            }
        }
    }

    private fun getLastLocation() {
        try {
            val criteria = Criteria()
            val provider = locationManager?.getBestProvider(criteria, false)
            val location = locationManager?.getLastKnownLocation(provider)
            Log.e(TAG, provider)
            Log.e(TAG, location?.toString() ?: "NO LastLocation")
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    private fun findUnAskedPermissions(wanted: java.util.ArrayList<String>): java.util.ArrayList<String> {
        val result = java.util.ArrayList<String>()
        for (perm in wanted) {
            if (hasPermission(perm)) {
                result.add(perm)
            }
        }
        return result
    }

    fun getLocation() {
        try {
            if (canGetLocation) {
                Log.e(TAG, "Can get location")
                if (isGPS) {
                    Log.e(TAG, "GPS on")
                    locationManager?.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES.toFloat(), this
                    )

                    if (locationManager != null) {
                        loc = locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                        if (loc != null) {
                            Log.e("GPS",loc?.latitude.toString()+"  "+loc?.longitude)
                            updateUI(loc!!)
                        }
                    }
                } else if (isNetwork) {
                    Log.e("GPS","Network on")
                    locationManager?.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES.toFloat(), this
                    )
                    if (locationManager != null) {
                        loc = locationManager?.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                        if (loc != null)
                            updateUI(loc!!)
                    }
                } else {
                    Log.e("GPS","else")
                    loc?.setLatitude(0.0)
                    loc?.setLongitude(0.0)
                    updateUI(loc!!)
                }
            }
        } catch (e: SecurityException) {
            e.printStackTrace()
        }

    }

    private fun hasPermission(permission: String): Boolean {
        if (canAskPermission()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED
            }
        }
        return false
    }

    private fun canAskPermission(): Boolean {
        return Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1
    }

    override fun onLocationChanged(location: Location?) {
        //updateUI(location!!)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

    override fun onProviderEnabled(provider: String?) {
        getLocation()
    }

    override fun onProviderDisabled(provider: String?) {
        if (locationManager != null) {
            locationManager!!.removeUpdates(this)
        }
    }
}
