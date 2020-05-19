package com.sahil.dailyneed.shop.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.sahil.dailyneed.R
import com.sahil.dailyneed.shop.adapter.RemoveAdapter
import kotlinx.android.synthetic.main.fragment_item.*

/**
 * A simple [Fragment] subclass.
 */
class ItemFragment : Fragment() {

    var shopList:MutableMap<String, List<String>> = HashMap<String, List<String>>();
    //private val
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        shopList.put("grocery", arrayListOf("Sugar", "Rice", "Pulses","Fruits", "Biscuits", "Tea", "Soap", "Bread", "Pasta"));
        shopList.put("healthcare", arrayListOf("Acetaminophen", "Adderall", "Alprazolam" , "Amitriptyline",
                    "Sanitizer", "Hand Wash", "Body Care", "Pain killer"));
        return inflater.inflate(R.layout.fragment_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        item_list_recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = RemoveAdapter(shopList.getValue("grocery"))
        }
    }


}
