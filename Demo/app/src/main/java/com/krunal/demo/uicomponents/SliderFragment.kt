package com.krunal.demo.uicomponents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentSliderBinding

class SliderFragment : Fragment() {

    private lateinit var binding: FragmentSliderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSliderBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBars()
    }

    private fun setupBars() {
        binding.sliderNormal.addOnChangeListener { _, value, _ ->
            binding.tvSliderNormal.text = getString(R.string.slider_value, value)
        }

        binding.sliderDiscrete.addOnChangeListener { _, value, _ ->
            binding.tvSliderDiscrete.text = getString(R.string.slider_value, value)
        }

        binding.sliderRange.addOnChangeListener { _, _, _ ->
            val values = binding.sliderRange.values
            binding.tvSliderRange.text = getString(
                R.string.slider_range_value, values[0], values[1]
            )
        }

        binding.sliderRangeDiscrete.addOnChangeListener { _, _, _ ->
            val values = binding.sliderRangeDiscrete.values
            binding.tvSliderRangeDiscrete.text = getString(
                R.string.slider_range_value, values[0], values[1]
            )
        }
    }
}