package com.krunal.demo.githubclient.ui.fragment

import androidx.fragment.app.viewModels
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentGithubHomeBinding
import com.krunal.demo.githubclient.ui.base.BaseFragment
import com.krunal.demo.githubclient.ui.viewmodel.GitHubHomeViewModel

class GitHubHomeFragment : BaseFragment<FragmentGithubHomeBinding, GitHubHomeViewModel>() {

    override val viewModel: GitHubHomeViewModel by viewModels()

    override fun getLayoutResId(): Int = R.layout.fragment_github_home
}