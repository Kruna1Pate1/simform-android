package com.krunal.demo.navigation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentTitleScreenBinding
import com.krunal.demo.navigation.ui.viewmodels.TitleScreenViewModel

class TitleScreenFragment : Fragment(R.layout.fragment_title_screen) {

    private lateinit var binding: FragmentTitleScreenBinding
    private val viewModel: TitleScreenViewModel by viewModels()

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
                val userProfile = viewModel.userProfile.value
                findNavController().navigate(
                    if (userProfile == null) {
                        TitleScreenFragmentDirections.actionTitleScreenFragmentToRegisterFragment()
                    } else {
                        TitleScreenFragmentDirections.actionTitleScreenFragmentToMatchFragment(
                            userProfile.id
                        )
                    }
                )
            }

            btnAbout.setOnClickListener {
                // TODO: Go to about
            }
        }
    }
}