package com.sahil.dailyneed.shop.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sahil.dailyneed.R
import com.sahil.dailyneed.activity.api.Retro
import com.sahil.dailyneed.shop.model.RegisterModel
import com.sahil.dailyneed.util.SessionManger
import kotlinx.android.synthetic.main.activity_add_details.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddDetailsActivity : AppCompatActivity(), View.OnClickListener, Callback<RegisterModel> {
    lateinit var sessionManager: SessionManger
    lateinit var namee_body: RequestBody
    lateinit var shop_type_body: RequestBody
    lateinit var city_type_body: RequestBody
    lateinit var user_id_body: RequestBody
    lateinit var user_id: String
    var body1: MultipartBody.Part? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_details)
        sessionManager = SessionManger(this)
        user_id = intent.getStringExtra("user_id")
        clickfun()
    }

    private fun clickfun() {
        btn_add_detials.setOnClickListener(this)
        shop_type.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_add_detials -> {
                if (shop_name.text.isNullOrEmpty()) {


                } else if (city_register.text.isNullOrEmpty()) {

                } else if (shop_type.text.isNullOrEmpty()) {

                } else {
                    apihit()
                }
                //                var email = response.body()!!.data.email
//                var name=response.body()!!.data.full_name
//                var user_id = response.body()!!.data.user_id
//                sessionManager.CreateInstallerLogin(false, true, email, name, user_id)


            }
            R.id.shop_type -> {
                val builder = androidx.appcompat.app.AlertDialog.Builder(this)
                builder.setTitle("Select")

                val tench =
                    arrayOf(
                        "Grocery",
                        "Medical"

                    )
                builder.setItems(tench) { dialog, which ->
                    when (which) {
                        0 -> {
                            shop_type.setText(tench[0])
                            dialog.dismiss()
                        }
                        1 -> {
                            shop_type.setText(tench[1])
                            dialog.dismiss()
                        }


                    }
                }

                val dialog = builder.create()
                dialog.show()
                return
            }
        }
    }

    private fun apihit() {
        add_progressbar.visibility = View.VISIBLE
        namee_body = RequestBody.create(
            MediaType.parse("text/plain"),
            shop_name.text.toString()
        ) as RequestBody
        user_id_body = RequestBody.create(
            MediaType.parse("text/plain"),
            user_id
        ) as RequestBody
        shop_type_body = RequestBody.create(
            MediaType.parse("text/plain"),
            shop_type.text.toString()
        ) as RequestBody
        city_type_body = RequestBody.create(
            MediaType.parse("text/plain"),
            city_register.text.toString()
        ) as RequestBody
        Retro.ApiService().addShop(namee_body, shop_type_body, user_id_body, city_type_body, body1)
            .enqueue(this)
    }

    override fun onFailure(call: Call<RegisterModel>, t: Throwable) {
        add_progressbar.visibility = View.GONE

    }

    override fun onResponse(call: Call<RegisterModel>, response: Response<RegisterModel>) {
        add_progressbar.visibility = View.GONE
        if (response.isSuccessful) {
            var intent = Intent(this, ShopHomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        }
    }
}
