package com.krunal.demo.webservices.gsonokhttp.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.krunal.demo.R

class DeleteConfirmationDialog(private val onConfirm: () -> Unit) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.delete_confirmation))
            .setCancelable(true)
            .setPositiveButton(getString(R.string.ok)) { _, _ -> onConfirm() }
            .create()

    companion object {
        const val TAG = "DeleteConfirmationDialog"
    }
}