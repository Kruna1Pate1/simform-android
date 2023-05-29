package com.krunal.demo.navigation.ui.fragments

import android.graphics.ColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navOptions
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentMatchBinding
import com.krunal.demo.navigation.ui.viewmodels.MatchViewModel

class MatchFragment : Fragment() {

    private lateinit var binding: FragmentMatchBinding
    private val viewModel: MatchViewModel by viewModels()
    private val args: MatchFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMatchBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        viewModel.setUser(args.userId)
        viewModel.createMatch()

        binding.btnStart.setOnClickListener {
            viewModel.match.value?.let { match ->
                findNavController().navigate(MatchFragmentDirections.actionMatchFragmentToInGameFragment(match.matchId))
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().popBackStack(R.id.titleScreenFragment, false)
        }
    }
}