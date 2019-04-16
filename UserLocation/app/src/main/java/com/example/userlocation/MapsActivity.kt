package com.example.userlocation

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Toast
import android.Manifest
import android.content.Context
import android.content.DialogInterface

import android.os.Build
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import com.google.android.gms.common.api.GoogleApiClient

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


import android.location.Location
import android.location.LocationManager
import android.util.Log
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices


@Suppress("DEPRECATION")
class MapsActivity : AppCompatActivity(), OnMapReadyCallback, com.google.android.gms.location.LocationListener {

    private lateinit var mMap: GoogleMap
    var myloc:LatLng= LatLng(0.14,0.36)
    private var REQUEST_LOCATION_CODE = 101
    private var mGoogleApiClient: GoogleApiClient? = null
    private var mLocation: Location? = null
    private var mLocationRequest: LocationRequest? = null
    private val UPDATE_INTERVAL = (2 * 1000).toLong()  /* 10 secs */
    private val FASTEST_INTERVAL: Long = 2000 /* 2 sec */


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        buildGoogleApiClient()
        try {
            Log.e("hahaha","aaa")

        }catch (e:Exception){

        }finally {
            Log.e("hahaha","onclick")
            if (!checkGPSEnabled()) {
                Log.e("hahaha","enabled")
                return
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Log.e("hahaha","not enabled")
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    Log.e("hahaha","not checking")
                    getLocation()
                } else {
                    Log.e("hahaha","checking")
                    checkLocationPermission()
                }
            } else {
                getLocation()
            }
            val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
            mapFragment.getMapAsync(this)
        }

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.setOnMapClickListener(GoogleMap.OnMapClickListener { point ->
            Toast.makeText(
                this,
                point.latitude.toString() + ", " + point.longitude,
                Toast.LENGTH_SHORT
            ).show()
            //loc=point
            MainActivity.Location =point
            mMap.addMarker(MarkerOptions().position(point).title(""))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(point))
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(point, 19.0f))
            MainActivity.users?.add(users(MainActivity.Name).apply { this.location=point })
            //startActivity(Intent(this,MainActivity::class.java))
        })
    }

    @Synchronized
    private fun buildGoogleApiClient() {
        Log.e("hahaha","buildGoogleApiClient()")
        mGoogleApiClient = GoogleApiClient.Builder(this)
            .addApi(LocationServices.API)
            .build()

        mGoogleApiClient!!.connect()
    }

    override fun onLocationChanged(location: Location?) {
        Log.e("hahaha","onLocationChanged(location")
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        Log.e("hahaha","getLocation")
        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (mLocation == null) {
            startLocationUpdates()
        }
        if (mLocation != null) {
            myloc= LatLng(mLocation!!.latitude, mLocation!!.longitude)
            mMap.addMarker(MarkerOptions().position(myloc).title(""))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(myloc))
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myloc, 19.0f))

        } else {
            Toast.makeText(this, "Location not Detected", Toast.LENGTH_SHORT).show();
        }
    }


    private fun checkGPSEnabled(): Boolean {
        Log.e("hahaha","GPSEnabled")
        if (!isLocationEnabled())
            showAlert()
        return isLocationEnabled()
    }

    private fun showAlert() {
        Log.e("hahaha","show alert")
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Enable Location")
            .setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " + "use this app")
            .setPositiveButton("Location Settings") { paramDialogInterface, paramInt ->
                val myIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(myIntent)
            }
            .setNegativeButton("Cancel") { paramDialogInterface, paramInt -> }
        dialog.show()
    }
    private fun isLocationEnabled(): Boolean {
        Log.e("hahaha","is loc available")
        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager!!.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER)
    }
    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                AlertDialog.Builder(this)
                    .setTitle("Location Permission Needed")
                    .setMessage("This app needs the Location permission, please accept to use location functionality")
                    .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION_CODE)
                    })
                    .create()
                    .show()

            } else ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_LOCATION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "permission granted", Toast.LENGTH_LONG).show()
                        getLocation()
                    }
                } else {
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show()
                }
                return
            }
        }
    }

    override fun onStart() {
        Log.e("hahaha","onStart")
        super.onStart()
        mGoogleApiClient?.connect()
    }

    override fun onStop() {
        Log.e("hahaha","onSTOP")
        super.onStop()
        if (mGoogleApiClient!!.isConnected()) {
            mGoogleApiClient!!.disconnect()
        }
    }

    private fun startLocationUpdates() {
        Log.e("hahaha","startLocationUpdates")
        mLocationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(UPDATE_INTERVAL)
            .setFastestInterval(FASTEST_INTERVAL)
        Log.e("hahaha","startLocationUpdates1")
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.e("hahaha","startLocationUpdates2")
            return
        }
        Log.e("hahaha","startLocationUpdates3")
        //mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
       LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this)
        Log.e("hahaha","startLocationUpdates4")
    }


}
