package com.sahil.dailyneed.user.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sahil.dailyneed.R
import com.sahil.dailyneed.common.UserTypeActivity
import com.sahil.dailyneed.user.activity.AccountActivity
import com.sahil.dailyneed.util.SessionManger
import kotlinx.android.synthetic.main.fragment_profile_user.*

/**
 * A simple [Fragment] subclass.
 */
class ProfileUserFragment : Fragment(), View.OnClickListener {
    lateinit var sessionManager: SessionManger

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_user, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sessionManager = SessionManger(context)
        user_name.text=sessionManager.uSerDeatis.get("name")

        account_relative.setOnClickListener(this)
        pp_relative.setOnClickListener(this)
        aboutus_relative.setOnClickListener(this)
        terms_relative.setOnClickListener(this)
        logout_relative.setOnClickListener(this)
        token_relative.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.account_relative -> {
                val intent = Intent(context!!, AccountActivity::class.java)
                intent.putExtra("profile", "account")
                startActivity(intent)

            }
            R.id.pp_relative -> {
                val intent = Intent(context!!, AccountActivity::class.java)
                intent.putExtra("profile", "pp")
                startActivity(intent)

            }
            R.id.aboutus_relative -> {
                val intent = Intent(context!!, AccountActivity::class.java)
                intent.putExtra("profile", "about")
                startActivity(intent)

            }
            R.id.terms_relative -> {
                val intent = Intent(context!!, AccountActivity::class.java)
                intent.putExtra("profile", "terms")
                startActivity(intent)

            }
            R.id.token_relative -> {

                val intent = Intent(context!!, AccountActivity::class.java)
                intent.putExtra("profile", "mytoken")
                startActivity(intent)

            }
            R.id.logout_relative -> {
                sessionManager.logoutUser()
                val intent = Intent(context!!, UserTypeActivity::class.java)
                startActivity(intent)
                activity!!.finish()

            }
        }
    }
}
