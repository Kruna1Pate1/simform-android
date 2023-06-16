package com.krunal.demo.uicomponents

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentToastBinding

class ToastFragment : Fragment(R.layout.fragment_toast) {
    private lateinit var binding: FragmentToastBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentToastBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
    }

    // Set click listeners for buttons to show toast
    private fun setupButtons() {
        // Short toast
        binding.btnShortToast.setOnClickListener {
            Toast.makeText(requireContext(), "This is short Toast", Toast.LENGTH_SHORT).show()
        }

        // Long toast
        binding.btnLongToast.setOnClickListener {
            Toast.makeText(requireContext(), "This is long Toast", Toast.LENGTH_LONG).show()
        }

        // Custom short success toast
        binding.btnSuccessToast.setOnClickListener {
                showToast(R.drawable.ic_check, "Custom success toast", backgroundColor = Color.GREEN)
        }

        // Custom long error toast
        binding.btnErrorToast.setOnClickListener {
            showToast(R.drawable.ic_cross, "Custom error toast", Toast.LENGTH_LONG, Color.RED)
        }
    }

    // Make and show custom toast
    private fun showToast(iconId: Int, text: String, length: Int = Toast.LENGTH_SHORT, backgroundColor: Int = Color.GRAY) {
        val toastView = layoutInflater.inflate(R.layout.custom_toast, requireActivity().findViewById(R.id.customToast))
        val imgViewStatus = toastView.findViewById<ImageView>(R.id.imgViewStatus)
        val tvMessage = toastView.findViewById<TextView>(R.id.tvMessage)

        toastView.setBackgroundColor(backgroundColor)
        imgViewStatus.setImageResource(iconId)
        tvMessage.text = text

        Toast(requireContext()).apply {
            setGravity(Gravity.CENTER, 0, 0)
            duration = length
            view = toastView
            show()
        }
    }
}