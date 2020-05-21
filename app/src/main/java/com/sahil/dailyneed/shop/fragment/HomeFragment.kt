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
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        tabLayout_shop.addTab(tabLayout_shop.newTab().setText("Request"))
        tabLayout_shop.addTab(tabLayout_shop.newTab().setText("Items"))

        tabLayout_shop.setTabGravity(TabLayout.GRAVITY_FILL)



        val adapter = MyAdapter(context, activity!!.supportFragmentManager, tabLayout_shop.getTabCount())
        viewPager_shop.setAdapter(adapter)

        viewPager_shop.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout_shop))

        tabLayout_shop.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager_shop.setCurrentItem(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

}
