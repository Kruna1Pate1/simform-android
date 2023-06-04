package com.krunal.demo.uicomponents.tablayoutfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabs()
    }

    private fun setupTabs() {
        // Text tabs programmatically
        listOf("Tab1", "Tab2", "Tab3", "Tab4").forEach {
            binding.tabLayoutText.apply {
                addTab(newTab().setText(it))
            }
        }

        // Show/Hide lable
        binding.tabLayoutSelectedText.apply {
            for (i in 0..tabCount) {
                if (i != selectedTabPosition) {
                    getTabAt(i)?.tabLabelVisibility = TabLayout.TAB_LABEL_VISIBILITY_UNLABELED
                }
            }
        }
        binding.tabLayoutSelectedText.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.tabLabelVisibility = TabLayout.TAB_LABEL_VISIBILITY_LABELED
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.tabLabelVisibility = TabLayout.TAB_LABEL_VISIBILITY_UNLABELED
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        // Scrollable tabs
        listOf("Tab1", "Tab2", "Tab3").forEach {
            binding.tabLayoutScrollable.apply {
                addTab(newTab().setText(it))
            }
        }
    }
}