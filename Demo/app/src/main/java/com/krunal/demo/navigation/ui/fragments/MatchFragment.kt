package com.krunal.demo.navigation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.krunal.demo.databinding.FragmentMatchBinding
import com.krunal.demo.navigation.ui.viewmodels.MatchViewModel

class MatchFragment : Fragment() {

    private lateinit var binding: FragmentMatchBinding
    private val viewModel: MatchViewModel by viewModels()

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
        binding.btnStart.setOnClickListener {
            // TODO: Start match 
        }
    }
}