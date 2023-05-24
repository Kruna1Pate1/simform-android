package com.krunal.demo.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentYoutubeBinding
import com.krunal.demo.recyclerview.adapters.YoutubeFragmentAdapter
import com.krunal.demo.recyclerview.viewmodels.YoutubeFragmentViewModel

class YoutubeFragment : Fragment() {

    private lateinit var binding: FragmentYoutubeBinding
    private val viewModel: YoutubeFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentYoutubeBinding.inflate(layoutInflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        setupViewPager()
        setupBottomNav()
    }

    private fun setupViewPager() {
        binding.vpFragment.adapter = YoutubeFragmentAdapter(
            listOf(YoutubeHomeFragment(), YoutubeHomeFragment(), YoutubeLibraryFragment()),
            requireActivity()
        )

        binding.vpFragment.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.bottomNavigation.menu.findItem(
                    when (position) {
                        0 -> R.id.actionHome
                        1 -> R.id.actionSubscription
                        else -> R.id.actionLibrary
                    }
                ).isChecked = true
            }
        })
    }

    private fun setupBottomNav() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.actionHome -> binding.vpFragment.currentItem = 0
                R.id.actionSubscription -> binding.vpFragment.currentItem = 1
                R.id.actionLibrary -> binding.vpFragment.currentItem = 2
            }
            true
        }
    }
}