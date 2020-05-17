package com.sahil.dailyneed.common

import android.Manifest
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import com.sahil.dailyneed.R
import com.sahil.dailyneed.shop.activity.ShopHomeActivity
import com.sahil.dailyneed.user.activity.MainActivity
import com.sahil.dailyneed.util.LocationTrack
import com.sahil.dailyneed.util.SessionManger
import java.util.*


class SplashActivity : AppCompatActivity() {
    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 3000
    lateinit var sessionManger: SessionManger


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setContentView(R.layout.activity_splash)


        mDelayHandler = Handler()

        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)
//        if (ContextCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            )
//            != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
//                PERMISSION_CODE
//            )
//        } else {
//            checkForLocation()
//        }




    }











    internal val mRunnable: Runnable = Runnable {
        sessionManger = SessionManger(this@SplashActivity)
        if (sessionManger.isUserLogin) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            //  api()

            finish()
        } else if (sessionManger.isShoperLogin) {
            val intent = Intent(applicationContext, ShopHomeActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            val intent = Intent(applicationContext, UserTypeActivity::class.java)
            startActivity(intent)
            finish()
        }


    }

}
