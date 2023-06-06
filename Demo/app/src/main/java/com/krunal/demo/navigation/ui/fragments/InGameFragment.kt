package com.krunal.demo.navigation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.appbar.MaterialToolbar
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentInGameBinding
import com.krunal.demo.navigation.ui.viewmodels.InGameViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class InGameFragment : Fragment() {

    private lateinit var binding: FragmentInGameBinding
    private val viewModel: InGameViewModel by viewModels()
    private val args: InGameFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentInGameBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        viewModel.loadMatch(args.matchId)

        binding.apply {
            btnWin.setOnClickListener {
                findNavController().navigate(InGameFragmentDirections.actionInGameFragmentToResultWinnerFragment())
                viewModel?.win()
            }

            btnLoss.setOnClickListener {
                findNavController().navigate(InGameFragmentDirections.actionInGameFragmentToGameOverFragment())
                viewModel?.loss()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().popBackStack(R.id.titleScreenFragment, false)
        }
    }
}