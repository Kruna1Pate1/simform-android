package com.krunal.demo.githubclient.ui.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.krunal.demo.R


class LogoutConfirmationDialog(private val onConfirm: () -> Unit) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        MaterialAlertDialogBuilder(requireContext(), R.style.AlertPrimary)
            .setMessage(getString(R.string.confirm_logout))
            .setCancelable(true)
            .setPositiveButton(getString(R.string.logout)) { _, _ -> onConfirm() }
            .create()

    companion object {
        const val TAG = "LogoutConfirmationDialog"
    }
}