package com.krunal.demo.uicomponents.adapters

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.krunal.demo.uicomponents.tablayoutfragments.CallsFragment
import com.krunal.demo.uicomponents.tablayoutfragments.ChatFragment
import com.krunal.demo.uicomponents.tablayoutfragments.HomeFragment

class ViewPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {

    private val fragments = listOf(HomeFragment(), ChatFragment(), CallsFragment())

    override fun getItemCount() = fragments.count()

    override fun createFragment(position: Int) = fragments[position]
}