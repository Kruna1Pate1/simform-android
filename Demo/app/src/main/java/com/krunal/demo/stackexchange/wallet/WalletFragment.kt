package com.krunal.demo.stackexchange.wallet

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.krunal.demo.databinding.FragmentWalletBinding
import com.krunal.demo.stackexchange.adapters.PortfolioAdapter
import com.krunal.demo.stackexchange.models.ShareDetails
import kotlinx.coroutines.launch

class WalletFragment : Fragment() {

    private lateinit var binding: FragmentWalletBinding
    private val viewModel: WalletFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentWalletBinding.inflate(layoutInflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPortfolio()
    }

    private fun setupPortfolio() {
        val adapter = PortfolioAdapter()
        binding.rvPortfolio.adapter = adapter
        binding.rvPortfolio.addItemDecoration(object: DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL) {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.top += 16
                outRect.bottom += 16
            }
        })

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.portfolios.collect { list ->
                    list?.let { adapter.submitList(list) }
                }
            }
        }
    }
}