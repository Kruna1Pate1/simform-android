package com.krunal.demo.stackexchange.market

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.krunal.demo.databinding.FragmentMarketBinding
import java.text.NumberFormat
import java.util.Locale

class MarketFragment : Fragment() {

    private lateinit var binding: FragmentMarketBinding
    private val viewModel: MarketFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMarketBinding.inflate(layoutInflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        binding.priceLayout1.imgBtnAdd.setOnClickListener {
            viewModel.changeYourPriceValue(true)
        }

        binding.priceLayout1.imgBtnMinus.setOnClickListener {
            viewModel.changeYourPriceValue(false)
        }

        binding.priceLayout2.imgBtnAdd.setOnClickListener {
            viewModel.changeAmountValue(true)
        }

        binding.priceLayout2.imgBtnMinus.setOnClickListener {
            viewModel.changeAmountValue(false)
        }
    }
}