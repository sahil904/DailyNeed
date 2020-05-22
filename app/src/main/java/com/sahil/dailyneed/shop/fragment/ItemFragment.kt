package com.sahil.dailyneed.shop.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.sahil.dailyneed.R
import com.sahil.dailyneed.activity.api.Retro
import com.sahil.dailyneed.shop.activity.AddItem
import com.sahil.dailyneed.shop.adapter.RemoveAdapter
import com.sahil.dailyneed.shop.model.ItemModel
import kotlinx.android.synthetic.main.fragment_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class ItemFragment : Fragment(), Callback<ItemModel> {

    //var shopList:MutableMap<String, List<String>> = HashMap<String, List<String>>();
    lateinit var shop_id:String
    //private val
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        shopList.put("grocery", arrayListOf("Sugar", "Rice", "Pulses","Fruits", "Biscuits", "Tea", "Soap", "Bread", "Pasta"));
//        shopList.put("healthcare", arrayListOf("Acetaminophen", "Adderall", "Alprazolam" , "Amitriptyline",
//                    "Sanitizer", "Hand Wash", "Body Care", "Pain killer"));
        return inflater.inflate(R.layout.fragment_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        shop_id = arguments!!.getString("shop_id","1")!!

        Retro.ApiService().getShopItem(shop_id).enqueue(this)
        addItemInShop.setOnClickListener{
            val intent = Intent(context, AddItem::class.java)
            intent.putExtra("shop_id", shop_id)
            startActivity(intent)
        }
    }

    override fun onFailure(call: Call<ItemModel>, t: Throwable) {
        Toast.makeText(context, "Item didn't added. Please try again", Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(call: Call<ItemModel>, response: Response<ItemModel>) {
        var shopList = response.body()!!.data
        item_list_recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = RemoveAdapter(context!!, shopList)
        }
    }


}
