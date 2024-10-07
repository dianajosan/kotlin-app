package com.example.kotlinapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.kotlinapp.data.Page
import com.example.kotlinapp.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val viewPager2 = view.findViewById<ViewPager2>(R.id.viewPager2)
        val tabLayout =
            view.findViewById<TabLayout>(R.id.tabLayout)

        val pages = listOf(
            Page(FirstFragment(), "Tab 1"),
            Page(SecondFragment(), "Tab 2"),
            Page(ThirdFragment(), "Tab 3")
        )

        val adapter = ViewPagerAdapter(requireActivity(), pages)
        viewPager2.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = pages[position].title
        }.attach()

        return view
    }
}