package com.sahil.dailyneed.user.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sahil.dailyneed.R
import com.sahil.dailyneed.user.fragment.PolicyFragment
import com.sahil.dailyneed.user.fragment.ProfileFragment
import kotlinx.android.synthetic.main.activity_account.*

class AccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        getdata()
    }

    private fun getdata() {
        var getdata = intent.getStringExtra("profile")

        if (getdata.equals("account")) {
            callfragment(ProfileFragment(), "Account")

        } else if (getdata.equals("about")) {
            callfragment(PolicyFragment(), "About Us")
        } else if (getdata.equals("pp")) {
            callfragment(PolicyFragment(), "Privacy policy")
        } else if (getdata.equals("terms")) {
            callfragment(PolicyFragment(), "Terms and Condition")
        }

    }

    private fun callfragment(fragment: Fragment, s: String) {
        tv_profile_toolbar.setText(s)
        supportFragmentManager.beginTransaction().replace(R.id.framelayout_account, fragment, s)
            .commit()

    }
}
