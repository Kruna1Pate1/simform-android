package com.krunal.demo.uicomponents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentThemeBinding
import com.krunal.demo.uicomponents.adapters.ThemeAdapter
import com.krunal.demo.uicomponents.extentions.isDarkMode
import com.krunal.demo.uicomponents.helpers.ThemeHelper

class ThemeFragment : Fragment() {

    private lateinit var binding: FragmentThemeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentThemeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTheme()
    }

    private fun setupTheme() {
        val themes = ThemeHelper.getThemes(requireContext(), requireActivity().isDarkMode)
        val themeAdapter = ThemeAdapter(
            requireContext(), themes
        )

        binding.gvTheme.adapter = themeAdapter
        binding.gvTheme.setItemChecked(ThemeHelper.getThemeAccent().ordinal, true)
        binding.gvTheme.setSelection(ThemeHelper.getThemeAccent().ordinal)

        binding.gvTheme.setOnItemClickListener { _, _, position, _ ->
            ThemeHelper.setThemeAccent(themes[position].accentColor)
            // TODO: Restart activity
//            activity?.recreate()
        }
    }
}