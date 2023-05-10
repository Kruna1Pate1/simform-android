package com.krunal.demo.uicomponents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.krunal.demo.databinding.FragmentSpanBinding
import com.krunal.demo.databinding.FragmentSpinnerBinding

class SpanFragment : Fragment() {

    private lateinit var binding: FragmentSpanBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSpanBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSpans()
    }

    private fun setupSpans() {
        // TODO: Create spannable string
    }
}