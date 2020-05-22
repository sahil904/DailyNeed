package com.sahil.dailyneed.shop.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sahil.dailyneed.R
import com.sahil.dailyneed.shop.fragment.HomeFragment
import com.sahil.dailyneed.shop.fragment.ItemFragment
import com.sahil.dailyneed.shop.fragment.ProfileFragment
import com.sahil.dailyneed.shop.fragment.RequestFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_toolbar.*

class ShopHomeActivity : AppCompatActivity() {

    lateinit var shop_id: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_home)
        if(intent.getStringExtra("shop_id") == null)
            shop_id = "1"
        else
            shop_id = intent.getStringExtra("shop_id")
        var bundle = Bundle()
        bundle.putString("shop_id", shop_id)
        var homeFragment = HomeFragment()
        homeFragment.arguments = bundle
        callfragment(homeFragment, "Home")
        bottom_navigation.setOnNavigationItemReselectedListener { item ->
            when (item.itemId) {
                R.id.home_bottom -> {
                    var requestFragment = RequestFragment()
                    requestFragment.arguments = bundle
                    callfragment(requestFragment, "Home")
                }
                R.id.user_bottom -> {
                    callfragment(ProfileFragment(), "Profile")

                } R.id.add_item -> {
                    var itemFragment = ItemFragment()
                    itemFragment.arguments = bundle
                    callfragment(itemFragment, "Item")

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
