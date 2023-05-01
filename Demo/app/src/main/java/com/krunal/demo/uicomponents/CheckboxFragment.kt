package com.krunal.demo.uicomponents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentCheckboxBinding

class CheckboxFragment : Fragment(R.layout.fragment_checkbox) {

    private lateinit var binding: FragmentCheckboxBinding
    private var selectedLanguages = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCheckboxBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCheckbox()
    }

    private fun setupCheckbox() {
        selectedLanguages.add(binding.cbEnglish.text.toString())
        selectedLanguages.add(binding.cbHindi.text.toString())
    }
}