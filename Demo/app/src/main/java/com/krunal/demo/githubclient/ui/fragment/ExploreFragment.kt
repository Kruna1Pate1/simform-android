package com.krunal.demo.githubclient.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentExploreBinding
import com.krunal.demo.databinding.FragmentProfileBinding
import com.krunal.demo.githubclient.ui.base.BaseFragment
import com.krunal.demo.githubclient.ui.viewmodel.ExploreViewModel
import com.krunal.demo.githubclient.ui.viewmodel.ProfileViewModel

class ExploreFragment : BaseFragment<FragmentExploreBinding, ExploreViewModel>() {

    override val viewModel: ExploreViewModel by viewModels()

    override fun getLayoutResId(): Int = R.layout.fragment_explore
}