package com.sahil.dailyneed.user.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sahil.dailyneed.R
import com.sahil.dailyneed.user.fragment.HomeUserFragment
import com.sahil.dailyneed.user.fragment.ProfileUserFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_toolbar.*

class MainActivity : AppCompatActivity() {
    val PERMISSION_ID = 42
    lateinit var mFusedLocationClient: FusedLocationProviderClient
    var lat: String = "0"
    var long: String = "0"
    var homeUserFragment = HomeUserFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        getLastLocation()
        bottom_navigation.setOnNavigationItemReselectedListener { item ->
            when (item.itemId) {
                R.id.home_bottom_user -> {
                    var bundle = Bundle()
                    bundle.putString("lat", lat)
                    bundle.putString("long", long)
                    callfragment(homeUserFragment, "Home")
                    homeUserFragment.arguments = bundle
                }
                R.id.user_bottom_user -> {
                    callfragment(ProfileUserFragment(), "Profile")

                }
            }

        }

        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_bottom_user -> {
                    callfragment(HomeUserFragment(), "Home")
                    // Respond to navigation item 1 click
                    true
                }
                R.id.user_bottom_user -> {
                    callfragment(ProfileUserFragment(), "Profile")
                    // Respond to navigation item 2 click
                    true
                }
                else -> false
            }
        }

    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {

                mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                    var location: Location? = task.result
                    if (location == null) {
                        requestNewLocationData()
                    } else {


                        Log.d("location.longit", location.latitude.toString())
                        Log.d("location.latitu)", location.longitude.toString())

                        var bundle = Bundle()
                        lat = location.latitude.toString()
                        long = location.longitude.toString()
                        bundle.putString("lat", lat)
                        bundle.putString("long", long)
                        callfragment(homeUserFragment, "Home")
                        homeUserFragment.arguments = bundle
                    }
                }
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {
        var mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationClient!!.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            var mLastLocation: Location = locationResult.lastLocation


            Log.d("mLastLocation.long", mLastLocation.longitude.toString())
            Log.d("mLastLocation.latitu)", mLastLocation.latitude.toString())
        }
    }

    private fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            PERMISSION_ID
        )
    }

    private fun callfragment(fragment: Fragment, s: String) {
        tv_custom_toolbar.setText(s)
        supportFragmentManager.beginTransaction().replace(R.id.framelayout_user, fragment, s)
            .commit()

    }
}
