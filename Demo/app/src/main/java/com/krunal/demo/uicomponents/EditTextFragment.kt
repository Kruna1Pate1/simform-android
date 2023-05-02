package com.krunal.demo.uicomponents

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.children
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentEditTextBinding
import com.krunal.demo.uicomponents.extentions.hideKeyboard


class EditTextFragment : Fragment(R.layout.fragment_edit_text) {

    private lateinit var binding: FragmentEditTextBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditTextBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        binding.root.setOnFocusChangeListener { view, isFocused ->
            if (isFocused) view.hideKeyboard()
        }

        binding.etEmail.setOnFocusChangeListener { emailView, isFocused ->
            val email = binding.etEmail.text.toString()
            binding.emailContainer.error = if (!isFocused && !isValidEmail(email)) {
                "Invalid email address"
            } else {
                null
            }
        }

        binding.etEmail.addTextChangedListener {
            if (it?.length == 0) binding.emailContainer.error = null
        }

        binding.btnClear.setOnClickListener {
            binding.root.children.forEach {
                if (it is EditText) {
                    it.text.clear()
                } else if (it is TextInputLayout) {
                    it.editText?.text?.clear()
                }
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
}