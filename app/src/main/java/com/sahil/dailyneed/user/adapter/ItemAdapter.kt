package com.sahil.dailyneed.user.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.sahil.dailyneed.R
import com.sahil.dailyneed.activity.api.Retro
import com.sahil.dailyneed.shop.model.ItemModel
import com.sahil.dailyneed.shop.model.Items
import com.sahil.dailyneed.shop.model.UserRequestModel
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call
import kotlinx.android.synthetic.main.custom_add_list_item.view.*
import kotlinx.android.synthetic.main.fragment_request.*

class ItemAdapter(var itemList: MutableList<Items> )
    : RecyclerView.Adapter<ItemAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.custom_add_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.add_item_name.setText(itemList.get(position).item_name)
        holder.itemView.addItemIcon.visibility= GONE
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}