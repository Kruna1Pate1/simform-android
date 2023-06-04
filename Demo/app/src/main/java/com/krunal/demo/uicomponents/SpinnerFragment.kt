package com.krunal.demo.uicomponents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentSpinnerBinding
import com.krunal.demo.uicomponents.adapters.TimezoneAdapter

class SpinnerFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: FragmentSpinnerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSpinnerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSpinners()
    }

    private fun setupSpinners() {
        ArrayAdapter.createFromResource(
            requireContext(), R.array.timezones, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerNormal.adapter = adapter
            binding.spinnerDialog.adapter = adapter
            binding.spinnerUnderlined.adapter = adapter
            binding.spinnerBordered.adapter = adapter
            binding.autoCompleteTimezone.setAdapter(adapter)
            binding.spinnerDisabled.adapter = adapter
        }

        binding.spinnerBordered.setSelection(4, true)
        binding.spinnerNormal.onItemSelectedListener = this
        binding.spinnerDisabled.isEnabled = false

        val adapter = TimezoneAdapter(requireContext(), resources.getStringArray(R.array.timezones))
        binding.spinnerCustom.adapter = adapter
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Toast.makeText(
            requireContext(),
            binding.spinnerNormal.selectedItem.toString(),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Toast.makeText(requireContext(), getString(R.string.nothing_selected), Toast.LENGTH_SHORT)
            .show()
    }
}