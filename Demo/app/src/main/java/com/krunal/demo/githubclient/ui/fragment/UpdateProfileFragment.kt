package com.krunal.demo.githubclient.ui.fragment

import androidx.fragment.app.viewModels
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentUpdateProfileBinding
import com.krunal.demo.githubclient.ui.base.BaseFragment
import com.krunal.demo.githubclient.ui.viewmodel.UpdateProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateProfileFragment : BaseFragment<FragmentUpdateProfileBinding, UpdateProfileViewModel>() {

    override val viewModel: UpdateProfileViewModel by viewModels()

    override fun getLayoutResId(): Int = R.layout.fragment_update_profile

    override fun initialize() {
        super.initialize()
        viewModel.getUser()
    }
}