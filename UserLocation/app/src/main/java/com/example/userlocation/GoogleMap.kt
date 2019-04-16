package com.example.userlocation

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_google_map.*

class GoogleMap : AppCompatActivity(), OnMapReadyCallback {

    var marker: Marker?=null
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_map)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        marker=mMap.addMarker(MarkerOptions().position(MainActivity.LocationLatLang!!).title(""))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(MainActivity.LocationLatLang!!))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(MainActivity.LocationLatLang!!, 19.0f))

        mMap.setOnMapClickListener(GoogleMap.OnMapClickListener { point ->

            marker?.remove()
            Toast.makeText(
                this,
                point.latitude.toString() + ", " + point.longitude,
                Toast.LENGTH_SHORT
            ).show()
            MainActivity.LocationLatLang =point
            marker=mMap.addMarker(MarkerOptions().position(point).title(""))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(point))
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(point, 19.0f))

        })
        done.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}
