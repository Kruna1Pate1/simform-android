package com.krunal.demo.githubclient.ui.fragment

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.krunal.demo.R
import com.krunal.demo.databinding.FragmentCreateIssueBinding
import com.krunal.demo.githubclient.ui.base.BaseFragment
import com.krunal.demo.githubclient.ui.viewmodel.CreateIssueViewModel
import com.krunal.demo.githubclient.ui.viewmodel.GitHubClientViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CreateIssueFragment : BaseFragment<FragmentCreateIssueBinding, CreateIssueViewModel>() {

    private val args: CreateIssueFragmentArgs by navArgs()

    override val viewModel: CreateIssueViewModel by viewModels()
    private val activityViewModel: GitHubClientViewModel by activityViewModels()

    override fun getLayoutResId(): Int = R.layout.fragment_create_issue

    override fun initialize() {
        super.initialize()
        viewModel.repoName = args.repositoryName
        activityViewModel.setSubtitle(viewModel.repoName)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_toolbar_create_issue, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        viewModel.createIssue()
        return true
    }

    override fun onDetach() {
        super.onDetach()
        activityViewModel.setSubtitle("")
    }
}