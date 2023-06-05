package com.krunal.demo.navigation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.krunal.demo.databinding.FragmentResultWinnerBinding

class ResultWinnerFragment : Fragment() {

    private lateinit var binding: FragmentResultWinnerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultWinnerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        binding.apply {
            btnLeaderboard.setOnClickListener {
                // TODO: Go to leaderboard
            }

            btnPlay.setOnClickListener {
                // TODO: Play match again
            }
        }
    }
}