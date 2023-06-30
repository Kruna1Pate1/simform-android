package com.krunal.demo.webservices.gsonokhttp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.krunal.demo.R
import com.krunal.demo.appcomponents.utils.IntentData
import com.krunal.demo.databinding.FragmentUserRegisterBinding
import com.krunal.demo.webservices.gsonokhttp.ui.dialogs.DeleteConfirmationDialog
import com.krunal.demo.webservices.gsonokhttp.ui.viewmodels.UserRegisterViewModel

class UserDetailFragment : Fragment() {

    private lateinit var binding: FragmentUserRegisterBinding
    private val viewModel: UserRegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserRegisterBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.btnSubmit
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        arguments?.getInt(IntentData.USER_ID)?.toInt()?.let(viewModel::setUpdateUser)

        binding.btnDeleteAccount.setOnClickListener {
            DeleteConfirmationDialog {
                deleteUser()
            }.show(childFragmentManager, DeleteConfirmationDialog.TAG)
        }
    }

    private fun deleteUser() {
        viewModel.deleteUser { deleted ->
            if (deleted) {
                Toast.makeText(requireContext(), getString(R.string.user_delete_success, viewModel.userDetails.value.name), Toast.LENGTH_SHORT).show()
                parentFragmentManager.popBackStack()
            } else {
                Toast.makeText(requireContext(), getString(R.string.user_delete_failed, viewModel.userDetails.value.name), Toast.LENGTH_SHORT).show()
            }
        }
    }
}