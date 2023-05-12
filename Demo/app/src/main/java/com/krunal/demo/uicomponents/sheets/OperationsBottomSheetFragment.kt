package com.krunal.demo.uicomponents.sheets

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.krunal.demo.databinding.FragmentOperationsBottomSheetBinding

class OperationsBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentOperationsBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentOperationsBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("RestrictedApi", "VisibleForTests")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        (dialog as BottomSheetDialog).behavior.apply {
            isDraggable = true
            state = BottomSheetBehavior.STATE_EXPANDED
        }
        return dialog
    }
}