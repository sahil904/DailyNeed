package com.sahil.dailyneed.shop.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sahil.dailyneed.R
import com.sahil.dailyneed.activity.api.Retro
import com.sahil.dailyneed.interfaces.MyItemClickListener
import com.sahil.dailyneed.shop.adapter.UserTokenadapter
import com.sahil.dailyneed.shop.model.DataUserRequestModel
import com.sahil.dailyneed.shop.model.UserRequestModel
import kotlinx.android.synthetic.main.fragment_request.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class RequestFragment : Fragment(), Callback<UserRequestModel>, MyItemClickListener {
    private var user_list: ArrayList<DataUserRequestModel> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_request, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        apihit()
    }

    private fun apihit() {

        Retro.ApiService().shopKeeperToken("1").enqueue(this)
    }

    override fun onFailure(call: Call<UserRequestModel>, t: Throwable) {
        user_token_progress.visibility = View.GONE
    }

    override fun onResponse(call: Call<UserRequestModel>, response: Response<UserRequestModel>) {
        user_token_progress.visibility = View.GONE

        if (response.isSuccessful) {
            for (i in 0 until response.body()!!.data.size) {
                var details = response.body()!!.data.get(i)
                user_list.add(
                    DataUserRequestModel(
                        details.time,
                        details.token_id,
                        details.user_name
                    )
                )
            }


            //   for (i in 0 until  response.body()!!.data.)

            recylerview_request.adapter = UserTokenadapter(context!!, user_list, this)

        }
    }

    override fun onItemClick(pos: Int) {

    }

}
