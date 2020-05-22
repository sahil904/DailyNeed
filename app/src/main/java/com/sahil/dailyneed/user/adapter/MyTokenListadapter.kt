package com.sahil.dailyneed.user.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sahil.dailyneed.R
import com.sahil.dailyneed.interfaces.MyItemClickListener
import com.sahil.dailyneed.user.model.DataMyTokenModel
import com.sahil.dailyneed.user.model.DataShopListModel
import kotlinx.android.synthetic.main.coustom_my_token.view.*
import kotlinx.android.synthetic.main.custom_shop_list.view.*


class MyTokenListadapter(
    var context: Context,
    var list: ArrayList<DataMyTokenModel>
) :
    RecyclerView.Adapter<MyTokenListadapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.coustom_my_token,
                p0,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        //  return list.size
        return list.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        // p0.itemView.aoi_text.setText(list.get(p1).aoi)
        p0.itemView.token_user.text = list.get(p1).token_id
        p0.itemView.shop_name_text.text = list.get(p1).shop_name

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }

}