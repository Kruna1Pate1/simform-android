package com.krunal.demo.uicomponents.picker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import android.widget.TimePicker.OnTimeChangedListener
import androidx.fragment.app.Fragment
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentTimePickerBinding

class TimePickerFragment : Fragment(), OnTimeChangedListener {

    private lateinit var binding: FragmentTimePickerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentTimePickerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPicker()
    }

    private fun setupPicker() {
        // Set is 24 hour format
        binding.switch24hour.setOnCheckedChangeListener { _, isChecked ->
            binding.tpClock.setIs24HourView(isChecked)
            binding.tpSpinner.setIs24HourView(isChecked)
        }

        binding.tpClock.setOnTimeChangedListener(this)
        binding.tpSpinner.setOnTimeChangedListener(this)
    }

    override fun onTimeChanged(timePicker: TimePicker, hourOfDay: Int, minute: Int) {
        // Sync both time pickers
        if (timePicker == binding.tpClock) {
            binding.tpSpinner.hour = hourOfDay
            binding.tpSpinner.minute = minute
        } else {
            binding.tpClock.hour = hourOfDay
            binding.tpClock.minute = minute
        }

        // Update time text view
        binding.tvTime.text = if (timePicker.is24HourView) {
            getString(R.string.time, hourOfDay, minute, "")
        } else {
            getString(
                R.string.time,
                if (hourOfDay % 12 == 0) 12 else hourOfDay,
                minute,
                if (hourOfDay < 12) "AM" else "PM"
            )
        }
    }
}