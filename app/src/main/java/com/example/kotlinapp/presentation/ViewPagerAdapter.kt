package com.example.kotlinapp.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kotlinapp.data.Page


class ViewPagerAdapter(fragmentActivity: FragmentActivity, private val pages: List<Page>) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = pages.size
    override fun createFragment(position: Int): Fragment = pages[position].fragment
}