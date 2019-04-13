package com.example.getcurrentlatlang

import android.Manifest
import android.annotation.SuppressLint
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
import android.widget.TextView
import java.text.DateFormat
import java.util.ArrayList

class MainActivity : AppCompatActivity() , LocationListener {
     val TAG = "GPS"
    private val ALL_PERMISSIONS_RESULT = 101
    private val MIN_DISTANCE_CHANGE_FOR_UPDATES: Long = 10
    private val MIN_TIME_BW_UPDATES = (1000 * 60).toLong()

    lateinit var tvLatitude: TextView
    lateinit var tvLongitude:TextView
    lateinit var tvTime:TextView
    var locationManager: LocationManager? = null
    var loc: Location? = null
    var permissions = ArrayList<String>()
    lateinit var permissionsToRequest: ArrayList<String>
    var permissionsRejected = ArrayList<String>()
    var isGPS = false
    var isNetwork = false
    var canGetLocation = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvLatitude = findViewById(R.id.tvLatitude) as TextView
        tvLongitude = findViewById(R.id.tvLongitude) as TextView
        tvTime = findViewById(R.id.tvTime) as TextView

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
                        permissionsToRequest.toTypedArray() as Array<String>,
                        ALL_PERMISSIONS_RESULT
                    )
                    Log.e(TAG, "Permission requests")
                    canGetLocation = false
                }
            }
            getLocation()
        }
    }


    private fun updateUI(loc: Location) {

        tvLatitude.text = loc.latitude.toString()
        tvLongitude.text = loc.longitude.toString()
        tvTime.text = DateFormat.getTimeInstance().format(loc.time)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (locationManager != null) {
            locationManager?.removeUpdates(this)
        }
    }

    fun showSettingsAlert() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("GPS is not Enabled!")
        alertDialog.setMessage("Do you want to turn on GPS?")
        alertDialog.setPositiveButton("Yes") { dialog, which ->
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

    private fun findUnAskedPermissions(wanted: ArrayList<String>): ArrayList<String> {
        val result = ArrayList<String>()

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
                    // from GPS
                    Log.e(TAG, "GPS on")
                    locationManager?.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES.toFloat(), this
                    )

                    if (locationManager != null) {
                        loc = locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                        if (loc != null) {
                            updateUI(loc!!)
                        }
                    }
                } else if (isNetwork) {
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
                    loc?.setLatitude(0.0)
                    loc?.setLongitude(0.0)
                    updateUI(loc!!)
                }
            } else {
                Log.d(TAG, "Can't get location")
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
        updateUI(location!!)
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
