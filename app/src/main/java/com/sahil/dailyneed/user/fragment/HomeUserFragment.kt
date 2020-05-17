package com.sahil.dailyneed.user.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sahil.dailyneed.R
import com.sahil.dailyneed.interfaces.MyItemClickListener
import com.sahil.dailyneed.user.activity.ShopDeatilsActivity
import com.sahil.dailyneed.user.adapter.ShopListadapter
import kotlinx.android.synthetic.main.fragment_home_user.*

/**
 * A simple [Fragment] subclass.
 */
class HomeUserFragment : Fragment(), MyItemClickListener {
    var list: ArrayList<String> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_user, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyler_shop_list.adapter = ShopListadapter(context!!, list, this)
    }

    override fun onItemClick(pos: Int) {
        var intent = Intent(context, ShopDeatilsActivity::class.java)
        startActivity(intent)
    }
}
