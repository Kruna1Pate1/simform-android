package com.krunal.demo.uicomponents.picker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.krunal.demo.databinding.FragmentDatePickerBinding
import java.util.Date

class DatePickerFragment : Fragment() {

    private lateinit var binding: FragmentDatePickerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDatePickerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPicker()
    }

    private fun setupPicker() {
        binding.dpCalender.maxDate = Date().time
        binding.dpCalender.updateDate(2023, 1, 1)
    }
}