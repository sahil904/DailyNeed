package com.sahil.dailyneed.common

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.iid.FirebaseInstanceId
import com.sahil.dailyneed.R
import com.sahil.dailyneed.activity.api.Retro
import com.sahil.dailyneed.shop.activity.AddDetailsActivity
import com.sahil.dailyneed.shop.model.RegisterModel
import com.sahil.dailyneed.user.activity.MainActivity
import com.sahil.dailyneed.util.SessionManger
import kotlinx.android.synthetic.main.activity_signup.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity(), View.OnClickListener, Callback<RegisterModel> {
    lateinit var user_type: String
    lateinit var token_id: String
    lateinit var lat: String
    lateinit var long: String

    lateinit var sessionManager: SessionManger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        sessionManager = SessionManger(this)

        user_type = intent.getStringExtra("user_type")
        lat = intent.getStringExtra("lat")
        long = intent.getStringExtra("long")
        token_id = FirebaseInstanceId.getInstance().getToken()!!
        clickfun()
    }

    private fun clickfun() {
        btn_signup_fragment.setOnClickListener(this)
        im_back_signup.setOnClickListener(this)
        login_register_pg_text.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_signup_fragment -> {
                if (name_register.text.isNullOrEmpty()) {
                    Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show()
                } else if (email_register.text.isNullOrEmpty()) {
                    Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show()
                } else if (!isValidEmail(email_register.getText().toString())) {
                    Toast.makeText(this, "Please Enter vaild Email id", Toast.LENGTH_SHORT).show()
                } else if (mobile_register.text.isNullOrEmpty()) {
                    Toast.makeText(this, "enter Mobile No", Toast.LENGTH_SHORT).show()
                } else if (password_register.text.isNullOrEmpty()) {
                    Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show()
                } else if (confrim_password_register.text.isNullOrEmpty()) {
                    Toast.makeText(this, "Enter Confrim Password", Toast.LENGTH_SHORT).show()
                } else if (!confrim_password_register.text.toString().equals(password_register.text.toString())) {
                    Toast.makeText(
                        this,
                        "password or confrim password not match",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    signup_progressbar.visibility = View.VISIBLE
                    apifun()
                }


            }
            R.id.im_back_signup -> {
                onBackPressed()

            }
            R.id.login_register_pg_text -> {
                onBackPressed()
            }
        }
    }

    private fun apifun() {
        Retro.ApiService().registration(
            name_register.text.toString(),
            email_register.text.toString(),
            mobile_register.text.toString(),
            password_register.text.toString(),
            confrim_password_register.text.toString(),
            token_id,
            user_type,
            "Self",
            lat,
            long
        ).enqueue(this)
    }

    fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    override fun onFailure(call: Call<RegisterModel>, t: Throwable) {
        signup_progressbar.visibility = View.GONE
    }

    override fun onResponse(call: Call<RegisterModel>, response: Response<RegisterModel>) {
        signup_progressbar.visibility = View.GONE

        if (response.isSuccessful) {
            if(response.body()!!.result.equals(1)) {
                var email = response.body()!!.data.email
                var name = response.body()!!.data.user_name
                var user_id = response.body()!!.data.user_id
                if (user_type.equals("user")) {

                    sessionManager.CreateInstallerLogin(false, true, email, name, user_id)

                    var intent = Intent(this, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                } else {
                    sessionManager.CreateInstallerLogin(false, false, email, name, user_id)

                    var intent = Intent(this, AddDetailsActivity::class.java)
                    intent.putExtra("user_id",user_id)
                    intent.putExtra("lat",lat)
                    intent.putExtra("long",long)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                }
            }
        }
    }
}
