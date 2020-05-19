package com.sahil.dailyneed.shop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sahil.dailyneed.R
import com.sahil.dailyneed.interfaces.MyItemClickListener
import com.sahil.dailyneed.shop.model.DataUserRequestModel
import com.sahil.dailyneed.shop.model.UserRequestModel
import kotlinx.android.synthetic.main.coustom_user_token.view.*


class UserTokenadapter(
    var context: Context,
    var list: ArrayList<DataUserRequestModel>,
    var myItemClickListener: MyItemClickListener
) :
    RecyclerView.Adapter<UserTokenadapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.coustom_user_token,
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
        p0.itemView.date.setText(list.get(p1).time)
        p0.itemView.name.setText(list.get(p1).user_name)
        p0.itemView.token.setText(list.get(p1).token_id)

        p0.itemView.setOnClickListener { myItemClickListener.onItemClick(p1) }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }

}