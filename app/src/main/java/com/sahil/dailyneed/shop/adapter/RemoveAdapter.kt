package com.sahil.dailyneed.shop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.sahil.dailyneed.R
import com.sahil.dailyneed.activity.api.Retro
import com.sahil.dailyneed.shop.model.ItemModel
import com.sahil.dailyneed.shop.model.Items
import com.sahil.dailyneed.shop.model.MessageModel
import kotlinx.android.synthetic.main.current_item_list.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoveAdapter(var context: Context, private val list: List<Items>)
    : RecyclerView.Adapter<RemoveAdapter.RemoveItemListViewHolder>(), Callback<MessageModel> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemoveItemListViewHolder {

        return RemoveItemListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.current_item_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RemoveItemListViewHolder, position: Int) {
        holder.itemView.item_name.setText(list.get(position).item_name)
        holder.itemView.removeItem.setOnClickListener {
            Retro.ApiService().deleteItem(list.get(position).item_id).enqueue(this)
        }
    }

    override fun onFailure(call: Call<MessageModel>, t: Throwable) {
        Toast.makeText(context, "Item didn't added. Please try again", Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(call: Call<MessageModel>, response: Response<MessageModel>) {
        Toast.makeText(context, "Item is already added.", Toast.LENGTH_SHORT).show()

    }

    inner class RemoveItemListViewHolder (itemView: View) :RecyclerView.ViewHolder(itemView){

    }

}