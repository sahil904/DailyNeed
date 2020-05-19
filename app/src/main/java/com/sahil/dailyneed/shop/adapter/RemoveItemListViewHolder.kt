package com.sahil.dailyneed.shop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sahil.dailyneed.R

class RemoveItemListViewHolder (inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.current_item_list, parent, false)) {
        private var mItemView: TextView? = null
        private var mRemove: ImageView? = null

        init {
            mItemView = itemView.findViewById(R.id.item_name)
            mRemove = itemView.findViewById(R.id.removeItem)
        }

        fun bind(itemName: String) {
            mItemView?.text = itemName;
        }
}