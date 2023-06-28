package com.krunal.demo.githubclient.ui.fragment

import android.net.Uri
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toFile
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
import com.krunal.demo.githubclient.util.getPathFromUri
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.File

@AndroidEntryPoint
class CreateIssueFragment : BaseFragment<FragmentCreateIssueBinding, CreateIssueViewModel>() {

    private val args: CreateIssueFragmentArgs by navArgs()
    private val activityResultLauncher: ActivityResultLauncher<String> =
        registerActivityResultLauncher()

    override val viewModel: CreateIssueViewModel by viewModels()
    private val activityViewModel: GitHubClientViewModel by activityViewModels()

    override fun getLayoutResId(): Int = R.layout.fragment_create_issue

    override fun initialize() {
        super.initialize()
        viewModel.repoName = args.repositoryName
        activityViewModel.setSubtitle(viewModel.repoName)
        setupImagePicker()
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

    private fun registerActivityResultLauncher(): ActivityResultLauncher<String> {
        return registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                val bytes = requireContext().contentResolver.openInputStream(uri).use { inputStream ->
                    inputStream?.readBytes()
                }
                bytes?.let { viewModel.addImage(bytes) }
            }
        }
    }

    private fun setupImagePicker() {
        binding.btnAddImage.setOnClickListener {
            activityResultLauncher.launch("image/*")
        }
    }
}