package com.sahil.dailyneed.user.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sahil.dailyneed.R
import com.sahil.dailyneed.activity.api.Retro
import com.sahil.dailyneed.shop.model.UserRequestModel
import com.sahil.dailyneed.util.SessionManger
import kotlinx.android.synthetic.main.activity_shop_deatils.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShopDeatilsActivity : AppCompatActivity(), View.OnClickListener, Callback<UserRequestModel> {
    lateinit var user_id: String
    lateinit var sessionManager: SessionManger
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_deatils)
        sessionManager = SessionManger(this)
        user_id = sessionManager.uSerDeatis.get("id")!!
        clickfun()
        item_deatils_recyler
    }

    private fun clickfun() {
        book_now.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.book_now -> {
                var shop_id = intent.getStringExtra("shop_id")
                Retro.ApiService().shopBooking(shop_id, user_id).enqueue(this)
            }
        }
    }

    override fun onFailure(call: Call<UserRequestModel>, t: Throwable) {

    }

    override fun onResponse(call: Call<UserRequestModel>, response: Response<UserRequestModel>) {

    }
}
