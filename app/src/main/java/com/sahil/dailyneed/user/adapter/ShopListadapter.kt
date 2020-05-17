package com.sahil.dailyneed.user.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sahil.dailyneed.R
import com.sahil.dailyneed.interfaces.MyItemClickListener


class ShopListadapter(var context: Context, var list: ArrayList<String>, var myItemClickListener: MyItemClickListener) :
        RecyclerView.Adapter<ShopListadapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_shop_list, p0, false))
    }

    override fun getItemCount(): Int {
      //  return list.size
        return 20
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
       // p0.itemView.aoi_text.setText(list.get(p1).aoi)
          p0.itemView.setOnClickListener { myItemClickListener.onItemClick(p1) }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }

}