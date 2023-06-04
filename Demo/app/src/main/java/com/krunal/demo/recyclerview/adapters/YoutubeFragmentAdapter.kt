package com.krunal.demo.recyclerview.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class YoutubeFragmentAdapter(
    private val fragments: List<Fragment>, fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = fragments.count()

    override fun createFragment(position: Int): Fragment = fragments[position]
}