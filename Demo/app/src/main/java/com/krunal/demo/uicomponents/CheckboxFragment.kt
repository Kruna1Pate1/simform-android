package com.krunal.demo.uicomponents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.CompoundButton.OnCheckedChangeListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentCheckboxBinding

class CheckboxFragment : Fragment(R.layout.fragment_checkbox), OnCheckedChangeListener {

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
        setupListeners()
    }

    private fun setupListeners() {
        selectedLanguages.add("English")
        selectedLanguages.add("Hindi")
        binding.cbEnglish.setOnCheckedChangeListener(this)
        binding.cbHindi.setOnCheckedChangeListener(this)
        binding.cbGujarati.setOnCheckedChangeListener(this)
        binding.cbSpanish.setOnCheckedChangeListener(this)
        binding.btnSaveChanges.setOnClickListener {
            Toast.makeText(requireContext(), selectedLanguages.joinToString(), Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onCheckedChanged(btn: CompoundButton?, isChecked: Boolean) {
        val language = when (btn) {
            binding.cbEnglish -> "English"
            binding.cbHindi -> "Hindi"
            binding.cbGujarati -> "Gujarati"
            binding.cbSpanish -> "Spanish"
            else -> ""
        }
        if (isChecked) {
            selectedLanguages.add(language)
        } else {
            selectedLanguages.remove(language)
        }
    }
}