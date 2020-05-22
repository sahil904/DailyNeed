package com.sahil.dailyneed.user.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sahil.dailyneed.R
import com.sahil.dailyneed.activity.api.Retro
import com.sahil.dailyneed.shop.model.DataUserRequestModel
import com.sahil.dailyneed.shop.model.UserRequestModel
import com.sahil.dailyneed.user.adapter.MyTokenListadapter
import com.sahil.dailyneed.user.model.DataMyTokenModel
import com.sahil.dailyneed.user.model.DataShopListModel
import com.sahil.dailyneed.user.model.MyTokenModel
import com.sahil.dailyneed.util.SessionManger
import kotlinx.android.synthetic.main.fragment_mytoken.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class MytokenFragment : Fragment(), Callback<MyTokenModel> {
    var list: ArrayList<DataMyTokenModel> = ArrayList()

    private lateinit var sessionManger: SessionManger
    private lateinit var user_id: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mytoken, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sessionManger = SessionManger(context)
        user_id = sessionManger.uSerDeatis.get("id")!!
        Retro.ApiService().userTokenListing(user_id).enqueue(this)
    }

    override fun onFailure(call: Call<MyTokenModel>, t: Throwable) {

    }

    override fun onResponse(call: Call<MyTokenModel>, response: Response<MyTokenModel>) {
        if (response.isSuccessful) {
            for (i in 0 until response.body()!!.data.size) {
                var details = response.body()!!.data.get(i)
                list.add(
                    DataMyTokenModel(
                        details.shop_name,
                        details.time,
                        details.token_id,
                        details.user_name
                    )
                )


            }
            mytoken_recyler?.adapter = MyTokenListadapter(context!!, list)
        }
    }
}
