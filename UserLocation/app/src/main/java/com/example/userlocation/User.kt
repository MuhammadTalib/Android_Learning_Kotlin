package com.example.userlocation

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import com.google.android.gms.maps.model.LatLng
import java.util.*

class users{
    var Name:String?=null
    var location: LatLng?=null
    var PhNumber: String?=null
    var address:String?=null
    var link:String?=null

    public fun findaddress(context:Context){
        var lat = this.location?.latitude
        val lon = this.location?.longitude

        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses: List<Address>

        addresses = geocoder.getFromLocation(lat!!, lon!!, 1)

        val address = addresses[0].getAddressLine(0)
        val address2 = addresses[0].getAddressLine(1)
        val city = addresses[0].locality
        val state = addresses[0].adminArea
        val country = addresses[0].countryName
        val postalCode = addresses[0].postalCode
        val knownName = addresses[0].featureName


        val message =
            "Emergency situation. Call for help. My location is: " + address + "." + "http://maps.google.com/maps?saddr=" + lat + "," + lon
        this.address=address
        this.link="http://maps.google.com/maps?saddr=" + lat + "," + lon

    }

}