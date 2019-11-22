package com.example.green_space_audits.mainactivity

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }



    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        var x = 38.9897
        var y = -76.9378
        val sydney = LatLng(x, y)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in College Park Maryland"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))


        val cir = mMap.addCircle(
            CircleOptions()
                .center(LatLng(x, y))
                .strokeColor(Color.RED)
                .fillColor(Color.BLUE)
        )
//
//        // Position the map's camera near Alice Springs in the center of Australia,
//        // and set the zoom factor so most of Australia shows on the screen.

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(x, y), 15f))

//
//        // Set listeners for click events.

    }



}