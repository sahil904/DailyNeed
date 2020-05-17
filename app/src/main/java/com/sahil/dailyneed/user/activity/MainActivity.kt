package com.sahil.dailyneed.user.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sahil.dailyneed.R
import com.sahil.dailyneed.user.fragment.HomeUserFragment
import com.sahil.dailyneed.user.fragment.ProfileUserFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_toolbar.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callfragment(HomeUserFragment(), "Home")
        bottom_navigation.setOnNavigationItemReselectedListener { item ->
            when (item.itemId) {
                R.id.home_bottom -> {
                    callfragment(HomeUserFragment(), "Home")
                }
                R.id.user_bottom -> {
                    callfragment(ProfileUserFragment(), "Profile")

                }
            }

        }

    }


    private fun callfragment(fragment: Fragment, s: String) {
        tv_custom_toolbar.setText(s)
        supportFragmentManager.beginTransaction().replace(R.id.framelayout_user, fragment, s)
            .commit()

    }
}
