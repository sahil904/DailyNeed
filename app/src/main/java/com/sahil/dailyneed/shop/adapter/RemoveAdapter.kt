package com.sahil.dailyneed.shop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RemoveAdapter(private val list: List<String>)
    : RecyclerView.Adapter<RemoveItemListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemoveItemListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RemoveItemListViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RemoveItemListViewHolder, position: Int) {
        val item:String = list[position]
        holder.bind(item)
    }

}