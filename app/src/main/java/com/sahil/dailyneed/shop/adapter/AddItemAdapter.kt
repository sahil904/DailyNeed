package com.sahil.dailyneed.shop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.sahil.dailyneed.R
import com.sahil.dailyneed.activity.api.Retro
import com.sahil.dailyneed.shop.model.ItemModel
import com.sahil.dailyneed.shop.model.UserRequestModel
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call
import kotlinx.android.synthetic.main.custom_add_list_item.view.*
import kotlinx.android.synthetic.main.fragment_request.*

class AddItemAdapter(var context: Context, var itemList: List<String>, var shop_id: String )
    : RecyclerView.Adapter<AddItemAdapter.ViewHolder>(), Callback<ItemModel>{

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
        holder.itemView.add_item_name.setText(itemList.get(position))
        holder.itemView.addItemIcon.setOnClickListener{
            Retro.ApiService().addItem(shop_id,itemList.get(position)).enqueue(this)
        }
    }

    override fun onFailure(call: Call<ItemModel>, t: Throwable) {
        Toast.makeText(context, "Item didn't added. Please try again", Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(call: Call<ItemModel>, response: Response<ItemModel>) {
        if(response.body()!!.msg.contains("this item Name is already Added"))
        {
            Toast.makeText(context, "Item is already added.", Toast.LENGTH_SHORT).show()
        }else
        {
            Toast.makeText(context, response.body()!!.data.get(0).item_name+" added.", Toast.LENGTH_SHORT).show()
        }

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}