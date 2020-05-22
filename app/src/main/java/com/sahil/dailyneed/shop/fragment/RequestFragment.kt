package com.sahil.dailyneed.shop.fragment

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.sahil.dailyneed.R
import com.sahil.dailyneed.activity.api.Retro
import com.sahil.dailyneed.interfaces.MyItemClickListener
import com.sahil.dailyneed.shop.adapter.UserTokenadapter
import com.sahil.dailyneed.shop.model.DataUserRequestModel
import com.sahil.dailyneed.shop.model.MessageModel
import com.sahil.dailyneed.shop.model.UserRequestModel
import com.sahil.dailyneed.util.SessionManger
import kotlinx.android.synthetic.main.fragment_request.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class RequestFragment : Fragment(), Callback<UserRequestModel>, MyItemClickListener {
    private var user_list: ArrayList<DataUserRequestModel> = ArrayList()
    private lateinit var shop_id: String

    private lateinit var sessionManger: SessionManger
    private lateinit var user_id: String
    lateinit var builder: AlertDialog.Builder
    lateinit var alert: AlertDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_request, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sessionManger= SessionManger(context)
        user_id=sessionManger.uSerDeatis.get("id")!!
        shop_id = arguments!!.getString("shop_id","1")
        apihit()
    }

    private fun apihit() {

        Retro.ApiService().shopKeeperToken(shop_id).enqueue(this)
    }

    override fun onFailure(call: Call<UserRequestModel>, t: Throwable) {
        user_token_progress?.visibility = View.GONE
        no_request_shop?.visibility = View.VISIBLE
    }

    override fun onResponse(call: Call<UserRequestModel>, response: Response<UserRequestModel>) {

        if (response.isSuccessful) {
            user_token_progress?.visibility = View.GONE

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

            recylerview_request?.adapter = UserTokenadapter(context!!, user_list, this)

        }
    }

    override fun onItemClick(pos: Int) {
        builder = AlertDialog.Builder(context)

        val layoutInflater = layoutInflater

        val customView = layoutInflater.inflate(R.layout.dialog_token_delete, null)
        val yes = customView.findViewById(R.id.yes_dialog) as Button
        val no = customView.findViewById(R.id.no_dialog) as Button

        yes.setOnClickListener(View.OnClickListener {
            user_token_progress.visibility=View.VISIBLE
            Retro.ApiService().deleteToken(user_list.get(pos).token_id)
                .enqueue(object : Callback<MessageModel> {
                    override fun onFailure(call: Call<MessageModel>, t: Throwable) {
                        user_token_progress.visibility=View.GONE

                    }

                    override fun onResponse(
                        call: Call<MessageModel>,
                        response: Response<MessageModel>
                    ) {
                        if (response.isSuccessful) {
                            user_token_progress.visibility=View.GONE

                            alert.hide()
                            user_list.clear()
                            apihit()
                        }
                    }

                })
        })
        no.setOnClickListener(View.OnClickListener {
            alert.hide()
        })
        builder.setView(customView)
        alert = builder.create()
        alert.show()
    }



}
