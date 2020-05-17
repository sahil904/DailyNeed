package com.sahil.dailyneed.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.sahil.dailyneed.R
import kotlinx.android.synthetic.main.activity_forgot_pass.*

class ForgotPassActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass)

        click()
    }

    private fun click() {
        im_back_signup_forgot.setOnClickListener(this)
        btn_recover_password.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.im_back_signup_forgot -> {
                onBackPressed()
            }
            R.id.btn_recover_password -> {
                if(email_password_change.text.isNullOrEmpty()){
                    Toast.makeText(this,"Enter Email",Toast.LENGTH_SHORT).show()
                }
                else  if(!isValidEmail(email_password_change.getText().toString())){
                    Toast.makeText(this,"Invalid Email",Toast.LENGTH_SHORT).show()
                }
                else{
                    onBackPressed()
                }

            }
        }
    }
    fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}
