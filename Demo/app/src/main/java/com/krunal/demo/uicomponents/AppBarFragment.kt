package com.krunal.demo.uicomponents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.google.android.material.color.MaterialColors
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentAppBarBinding
import com.krunal.demo.uicomponents.extentions.getThemeColor

class AppBarFragment : Fragment(R.layout.fragment_app_bar) {

    private lateinit var binding: FragmentAppBarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentAppBarBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAppBars()
    }

    private fun setupAppBars() {
        binding.tbItems.inflateMenu(R.menu.toolbar_menu)
        val searchItem = binding.tbSearch.menu.findItem(R.id.miSearch)
        val searchView = searchItem.actionView as SearchView
        searchView.isIconified = false
    }
}