package com.sahil.dailyneed.user.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sahil.dailyneed.R
import kotlinx.android.synthetic.main.activity_shop_deatils.*

class ShopDeatilsActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_deatils)

        clickfun()
    }

    private fun clickfun() {
        book_now.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.book_now->{

            }
        }
    }
}
