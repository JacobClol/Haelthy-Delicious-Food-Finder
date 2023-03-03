package com.example.haelthydeliciousfoodfinder.ui.activities

import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.navArgs
import com.example.haelthydeliciousfoodfinder.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.haelthydeliciousfoodfinder.databinding.ActivityMapsBinding
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMapClickListener,
    GoogleMap.OnMapLongClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private val args: MapsActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val geocoder = Geocoder(this, Locale.getDefault())
        val addressList = geocoder.getFromLocationName(args.city, 1)
        if (!addressList.isNullOrEmpty()) {
            val address = addressList[0]
            val market = LatLng(address.latitude, address.longitude)
            mMap.addMarker(MarkerOptions().position(market).title("Marker in ${args.city}"))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(market))
        }

        mMap.setOnMapClickListener(this)
        mMap.setOnMapLongClickListener(this)
    }

    override fun onMapClick(p0: LatLng) {
        val geocoder = Geocoder(this, Locale.getDefault())
        val addressList = geocoder.getFromLocation(p0.latitude, p0.longitude, 1)
        val address = addressList?.get(0)
        val city = address?.locality
        val market = LatLng(p0.latitude, p0.longitude)
        mMap.clear()
        mMap.addMarker(MarkerOptions().position(market).title(city))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(p0))
    }

    override fun onMapLongClick(p0: LatLng) {
        val geocoder = Geocoder(this, Locale.getDefault())
        val addressList = geocoder.getFromLocation(p0.latitude, p0.longitude, 1)
        val address = addressList?.get(0)
        val city = address?.locality
        val market = LatLng(p0.latitude, p0.longitude)
        mMap.clear()
        mMap.addMarker(MarkerOptions().position(market).title(city))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(p0))
    }
}
