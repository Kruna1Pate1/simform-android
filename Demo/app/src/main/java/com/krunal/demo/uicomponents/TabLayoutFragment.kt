package com.krunal.demo.uicomponents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentTabLayoutBinding
import com.krunal.demo.uicomponents.adapters.ViewPagerAdapter

class TabLayoutFragment : Fragment(R.layout.fragment_tab_layout) {

    private lateinit var binding: FragmentTabLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentTabLayoutBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabsWithPager()
    }

    private fun setupTabsWithPager() {
        val viewPagerAdapter = ViewPagerAdapter(requireActivity())
        binding.viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Home"
                1 -> "Chat"
                else -> "Call"
            }
        }.attach()
    }
}