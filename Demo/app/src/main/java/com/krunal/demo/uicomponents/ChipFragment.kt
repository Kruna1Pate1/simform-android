package com.krunal.demo.uicomponents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentChipBinding

class ChipFragment : Fragment(R.layout.fragment_chip) {

    private lateinit var binding: FragmentChipBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChipBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupChips()
    }

    private fun setupChips() {
        binding.etChip.addTextChangedListener {
            binding.tilChip.isEndIconVisible = binding.etChip.text.isNullOrEmpty().not()
        }

        binding.etChip.setOnEditorActionListener { _, _, _ ->
            addChip()
            true
        }

        binding.tilChip.setEndIconOnClickListener {
            addChip()
        }
    }

    private fun addChip() {
        val chip = Chip(requireContext())
        chip.text = binding.etChip.text
        chip.setOnCloseIconClickListener { binding.cgProgrammatically.removeView(it) }
        chip.setCloseIconResource(R.drawable.ic_cross)
        chip.isCloseIconVisible = true
        binding.cgProgrammatically.addView(chip)
        binding.etChip.text?.clear()
    }
}