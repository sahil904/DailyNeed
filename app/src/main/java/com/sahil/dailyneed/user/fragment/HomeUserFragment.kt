package com.sahil.dailyneed.user.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sahil.dailyneed.R
import com.sahil.dailyneed.activity.api.Retro
import com.sahil.dailyneed.interfaces.MyItemClickListener
import com.sahil.dailyneed.user.activity.ShopDeatilsActivity
import com.sahil.dailyneed.user.adapter.ShopListadapter
import com.sahil.dailyneed.user.model.DataShopListModel
import com.sahil.dailyneed.user.model.ShopListModel
import com.sahil.dailyneed.util.SessionManger
import kotlinx.android.synthetic.main.fragment_home_user.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class HomeUserFragment : Fragment(), MyItemClickListener, Callback<ShopListModel> {
    var list: ArrayList<DataShopListModel> = ArrayList()

var lat:String?=null
var long:String?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_user, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        apihit()
    }

    private fun apihit() {
        lat=arguments!!.getString("lat")
        long=arguments!!.getString("long")
        Retro.ApiService().shopListing(lat!!,long!!).enqueue(this)
    }

    override fun onItemClick(pos: Int) {
        var intent = Intent(context, ShopDeatilsActivity::class.java)
        intent.putExtra("shop_id",list.get(pos).shop_id)
        startActivity(intent)
    }

    override fun onFailure(call: Call<ShopListModel>, t: Throwable) {
        Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(call: Call<ShopListModel>, response: Response<ShopListModel>) {

        if (response.isSuccessful) {

            for (i in 0 until response.body()!!.data.size) {
                var details = response.body()!!.data.get(i)
                list.add(
                    DataShopListModel(
                        details.city_name,
                        details.image_url,
                        details.shop_id,
                        details.shop_name,
                        details.shop_type
                    )
                )

            }
            recyler_shop_list?.adapter = ShopListadapter(context!!, list, this)

        }

    }
}
