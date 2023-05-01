package com.krunal.demo.stackexchange.exchange

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.krunal.demo.databinding.FragmentExchangeBinding

class ExchangeFragment : Fragment() {

    private lateinit var binding: FragmentExchangeBinding
    private val viewModel: ExchangeFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentExchangeBinding.inflate(layoutInflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()

    }

    private fun setupListener() {
        binding.cardExchange.exchangeView.cardAmount.apply {
            imgBtnAdd.setOnClickListener {
                viewModel.changeExchangeAmount(0.1F)
            }

            imgBtnMinus.setOnClickListener {
                viewModel.changeExchangeAmount(-0.1F)
            }

            imgBtnAdd.setOnLongClickListener {
                viewModel.changeExchangeAmount(1F)
                true
            }

            imgBtnMinus.setOnLongClickListener {
                viewModel.changeExchangeAmount(-1F)
                true
            }
        }

        binding.cardExchange.receiverView.cardAmount.apply {
            imgBtnAdd.setOnClickListener {
                viewModel.changeReceiveAmount(0.1F)
            }

            imgBtnMinus.setOnClickListener {
                viewModel.changeReceiveAmount(-0.1F)
            }

            imgBtnAdd.setOnLongClickListener {
                viewModel.changeReceiveAmount(1F)
                true
            }

            imgBtnMinus.setOnLongClickListener {
                viewModel.changeReceiveAmount(-1F)
                true
            }
        }
    }
}