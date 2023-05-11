package com.krunal.demo.uicomponents.cardscreen

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import com.krunal.demo.R
import com.krunal.demo.UIComponentsActivity
import com.krunal.demo.databinding.FragmentCardBinding

class CardFragment : Fragment() {

    private lateinit var binding: FragmentCardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBottomBar()
        (activity as? UIComponentsActivity)?.supportActionBar?.apply {
            title = getString(R.string.my_cards)
            setBackgroundDrawable(ColorDrawable(Color.parseColor("#2F7CEF")))
            elevation = 0f
            // TODO: Make title center
        }
    }

    private fun setupBottomBar() {
        binding.bottomNavigation.apply {
            selectedItemId = menu.getItem(1).itemId
            labelVisibilityMode = NavigationBarView.LABEL_VISIBILITY_SELECTED
        }
    }

}