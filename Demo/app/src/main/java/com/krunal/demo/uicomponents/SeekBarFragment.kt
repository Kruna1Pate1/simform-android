package com.krunal.demo.uicomponents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentSeekBarBinding

class SeekBarFragment : Fragment() {

    private lateinit var binding: FragmentSeekBarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSeekBarBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBars()
    }

    private fun setupBars() {
        binding.tvSliderNormal.text =
            getString(R.string.slider_value, binding.seekBarNormal.progress.toFloat())
        binding.tvSliderDiscrete.text =
            getString(R.string.slider_value, binding.sliderDiscrete.value)
        binding.tvSliderRange.text = getString(
            R.string.slider_range_value,
            binding.sliderRange.values[0],
            binding.sliderRange.values[1]
        )
        binding.tvSliderRangeDiscrete.text = getString(
            R.string.slider_range_value,
            binding.sliderRangeDiscrete.values[0],
            binding.sliderRangeDiscrete.values[1]
        )
    }
}