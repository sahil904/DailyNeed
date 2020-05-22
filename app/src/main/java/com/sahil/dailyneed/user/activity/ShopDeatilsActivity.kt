package com.sahil.dailyneed.user.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sahil.dailyneed.R
import com.sahil.dailyneed.activity.api.Retro
import com.sahil.dailyneed.shop.adapter.AddItemAdapter
import com.sahil.dailyneed.shop.model.ItemModel
import com.sahil.dailyneed.shop.model.UserRequestModel
import com.sahil.dailyneed.user.adapter.ItemAdapter
import com.sahil.dailyneed.user.model.RequestModel
import com.sahil.dailyneed.util.SessionManger
import kotlinx.android.synthetic.main.activity_shop_deatils.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class ShopDeatilsActivity : AppCompatActivity(), View.OnClickListener, Callback<RequestModel> {
    lateinit var user_id: String
    lateinit var shop_id: String
    lateinit var sessionManager: SessionManger
    var shopList: MutableMap<String, List<String>> = HashMap()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_deatils)
        sessionManager = SessionManger(this)
        user_id = sessionManager.uSerDeatis.get("id")!!
        clickfun()


    }

    private fun clickfun() {
        shop_id = intent.getStringExtra("shop_id")
        book_now.setOnClickListener(this)
        Retro.ApiService().getShopItem(shop_id).enqueue(object : retrofit2.Callback<ItemModel>{
            override fun onFailure(call: Call<ItemModel>, t: Throwable) {
                Toast.makeText(this@ShopDeatilsActivity, "Error to show list", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ItemModel>, response: Response<ItemModel>) {

                item_deatils_recyler.adapter = ItemAdapter(response.body()!!.data)
            }
        })
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.book_now -> {
                //var shop_id = intent.getStringExtra("shop_id")
                Retro.ApiService().shopBooking(shop_id, user_id).enqueue(this)
            }
        }
    }

    override fun onFailure(call: Call<RequestModel>, t: Throwable) {
        Toast.makeText(this, "server error", Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(call: Call<RequestModel>, response: Response<RequestModel>) {
        if (response.isSuccessful) {
            var intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "server error", Toast.LENGTH_SHORT).show()

        }
    }

}
