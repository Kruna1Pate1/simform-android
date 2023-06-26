package com.krunal.demo.githubclient.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.githubclient.data.local.IssueModel
import com.krunal.demo.githubclient.data.remote.model.response.IssueResponse
import com.krunal.demo.githubclient.data.repository.IssueRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateIssueViewModel @Inject constructor(
    private val issueRepository: IssueRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _issue = MutableStateFlow<IssueResponse?>(null)
    val issue = _issue.asStateFlow()

    private val _issueModel = MutableStateFlow(IssueModel(""))
    val issueModel = _issueModel.asStateFlow()

    var repoName: String = ""

    fun createIssue() {
        if (issueModel.value.title.isEmpty()) return

        viewModelScope.launch {
            issueRepository.createIssue(repoName, issueModel.value).collectLatest { resource ->

            }
        }
    }
}