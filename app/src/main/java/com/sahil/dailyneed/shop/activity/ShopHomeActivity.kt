package com.sahil.dailyneed.shop.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sahil.dailyneed.R
import com.sahil.dailyneed.shop.fragment.HomeFragment
import com.sahil.dailyneed.shop.fragment.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_toolbar.*

class ShopHomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_home)


        callfragment(HomeFragment(), "Home")
        bottom_navigation.setOnNavigationItemReselectedListener { item ->
            when (item.itemId) {
                R.id.home_bottom -> {
                    callfragment(HomeFragment(), "Home")
                }
                R.id.user_bottom -> {
                    callfragment(ProfileFragment(), "Profile")

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
