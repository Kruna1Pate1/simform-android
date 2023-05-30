package com.krunal.demo.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.krunal.demo.databinding.FragmentCalculateBinding
import com.krunal.demo.recyclerview.adapters.CalculationAdapter
import com.krunal.demo.recyclerview.listeners.OnItemChangeListener
import com.krunal.demo.recyclerview.viewmodels.CalculateViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CalculateFragment : Fragment(), OnItemChangeListener {

    private lateinit var binding: FragmentCalculateBinding
    private val viewModel: CalculateViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalculateBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCalculations()
    }

    private fun setupCalculations() {
        val adapter = CalculationAdapter().apply {
            onItemChangeListener = this@CalculateFragment
        }
        binding.rvMain.adapter = adapter

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.calculations.collectLatest { list ->
                    adapter.submitList(list)
                }
            }
        }
    }

    override fun onValueRemove(position: Int, valuePosition: Int) {
        viewModel.removeValue(position, valuePosition)
    }

    override fun onValueAdd(position: Int, value: Int) {
        viewModel.addValue(position, value)
    }

    override fun onValueChange(position: Int, valuePosition: Int, value: Int) {
        viewModel.updateValue(position, valuePosition, value)
    }
}