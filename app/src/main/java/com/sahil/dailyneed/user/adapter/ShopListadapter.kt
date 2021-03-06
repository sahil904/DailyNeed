package com.sahil.dailyneed.user.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sahil.dailyneed.R
import com.sahil.dailyneed.interfaces.MyItemClickListener
import com.sahil.dailyneed.user.model.DataShopListModel
import kotlinx.android.synthetic.main.custom_shop_list.view.*


class ShopListadapter(
    var context: Context,
    var list: ArrayList<DataShopListModel>,
    var myItemClickListener: MyItemClickListener
) :
    RecyclerView.Adapter<ShopListadapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.custom_shop_list,
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
        p0.itemView.shop_name_lis.text = list.get(p1).shop_name
        Glide.with(context).load(list.get(p1).image_url).error(R.drawable.home_icon)
            .placeholder(R.drawable.home_icon).into(p0.itemView.shop_image)
        p0.itemView.setOnClickListener { myItemClickListener.onItemClick(p1) }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }

}