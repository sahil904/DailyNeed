package com.sahil.dailyneed.shop.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.sahil.dailyneed.R
import com.sahil.dailyneed.shop.adapter.AddItemAdapter
import kotlinx.android.synthetic.main.activity_shop_items.*

class AddItem : AppCompatActivity() {

    var shopList:MutableMap<String, List<String>> = HashMap<String, List<String>>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_items)
        shopList.put("grocery", arrayListOf("Sugar", "Rice", "Pulses","Fruits", "Biscuits", "Tea", "Soap", "Bread", "Pasta"));
        shopList.put("healthcare", arrayListOf("Acetaminophen", "Adderall", "Alprazolam" , "Amitriptyline",
            "Sanitizer", "Hand Wash", "Body Care", "Pain killer"));
        addItemsRecylerView.adapter = AddItemAdapter(shopList.getValue("grocery"))
    }
}
