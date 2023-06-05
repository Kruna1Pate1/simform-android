package com.krunal.demo.navigation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.krunal.demo.databinding.FragmentTitleScreenBinding

class TitleScreenFragment : Fragment() {

    private lateinit var binding: FragmentTitleScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentTitleScreenBinding.inflate(layoutInflater, container, false)

        return binding.root
    }
}