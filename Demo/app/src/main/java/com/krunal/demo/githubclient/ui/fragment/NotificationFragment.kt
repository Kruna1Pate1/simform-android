package com.krunal.demo.githubclient.ui.fragment

import androidx.fragment.app.viewModels
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentProfileBinding
import com.krunal.demo.githubclient.ui.base.BaseFragment
import com.krunal.demo.githubclient.ui.viewmodel.ProfileViewModel

class NotificationFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {

    override val viewModel: ProfileViewModel by viewModels()

    override fun getLayoutResId(): Int = R.layout.fragment_profile
}