package com.sahil.dailyneed.shop.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

import com.sahil.dailyneed.R
import com.sahil.dailyneed.shop.adapter.MyAdapter

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        tabLayout.addTab(tabLayout.newTab().setText("Request"))
        tabLayout.addTab(tabLayout.newTab().setText("Items"))

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL)



        val adapter = MyAdapter(context, activity!!.supportFragmentManager, tabLayout.getTabCount())
        viewPager.setAdapter(adapter)

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.setCurrentItem(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

}
