package com.krunal.demo.uicomponents.constraintLayouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentClCircularBinding

class CircularFragment : Fragment(R.layout.fragment_cl_circular) {

    private lateinit var binding: FragmentClCircularBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentClCircularBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        binding.imgBtnMain.setOnCheckedChangeListener { _, isChecked ->
            binding.groupEmojis.visibility = if (isChecked) View.VISIBLE else View.INVISIBLE
        }
    }
}