package com.krunal.demo.uicomponents

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentButtonBinding

class ButtonFragment : Fragment(R.layout.fragment_button), OnClickListener {
    private lateinit var binding: FragmentButtonBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentButtonBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListener()
    }

    override fun onClick(view: View?) {
        (view as? MaterialButton)?.let {
            Toast.makeText(requireContext(), "${it.text} clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupClickListener() {

        binding.btnNormal.setOnClickListener(this)
        binding.btnBordered.setOnClickListener(this)
        binding.appCompatButton.setOnClickListener(this)
        binding.btnDisabled.setOnClickListener(this)
        binding.btnOutlined.setOnClickListener(this)
        binding.btnText.setOnClickListener(this)
        binding.imgBtnImage.setOnClickListener(this)
        binding.btnInfo.setOnClickListener(this)
        binding.btnGradient.setOnClickListener(this)

        binding.switchEnable.setOnCheckedChangeListener { btn, checked ->
            binding.root.children
                .filterNot { it == binding.switchEnable }
                .forEach { it.isEnabled = checked }
            btn.text = if (checked) getString(R.string.enabled_switch) else getString(R.string.disabled_switch)
        }
    }
}