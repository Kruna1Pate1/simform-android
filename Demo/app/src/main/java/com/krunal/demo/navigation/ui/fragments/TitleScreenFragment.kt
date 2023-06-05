package com.krunal.demo.navigation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentTitleScreenBinding

class TitleScreenFragment : Fragment(R.layout.fragment_title_screen) {

    private lateinit var binding: FragmentTitleScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentTitleScreenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        binding.apply {
            btnLeaderboard.setOnClickListener {
                findNavController().navigate(TitleScreenFragmentDirections.actionTitleScreenFragmentToLeaderboardFragment())
            }

            btnPlay.setOnClickListener {
                findNavController().navigate(TitleScreenFragmentDirections.actionTitleScreenFragmentToRegisterFragment())
            }

            btnAbout.setOnClickListener {
                // TODO: Go to about
            }
        }
    }
}