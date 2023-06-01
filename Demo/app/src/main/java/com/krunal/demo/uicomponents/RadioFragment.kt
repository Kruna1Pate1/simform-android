package com.krunal.demo.uicomponents

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.radiobutton.MaterialRadioButton
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentRadioBinding

class RadioFragment : Fragment(R.layout.fragment_radio) {

    private lateinit var binding: FragmentRadioBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRadioBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showSelected()
    }

    private fun showSelected() {
        val toast = Toast(requireContext())
        toast.duration = Toast.LENGTH_SHORT
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0)

        binding.rgGender.setOnCheckedChangeListener { _, id ->
            toast.setText(
                when (id) {
                    binding.radioMale.id -> "Male"
                    binding.radioFemale.id -> "Female"
                    else -> "Other"
                }
            )
            toast.show()
        }
        binding.rgHouse.setOnCheckedChangeListener { _, id ->
            toast.setText(
                binding.root.findViewById<MaterialRadioButton>(id).text
            )
            toast.show()
        }
    }
}