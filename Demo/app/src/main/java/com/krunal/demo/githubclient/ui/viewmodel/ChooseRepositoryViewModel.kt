package com.krunal.demo.githubclient.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.krunal.demo.githubclient.data.local.RepoCard
import com.krunal.demo.githubclient.data.repository.RepoRepository
import com.krunal.demo.githubclient.ui.base.BaseViewModel
import com.krunal.demo.webservices.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChooseRepositoryViewModel @Inject constructor(
    private val repoRepository: RepoRepository
) : BaseViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()


    private val _repositories: MutableStateFlow<List<RepoCard>> =
        MutableStateFlow(
            emptyList()
        )
    val repositories = _repositories.asStateFlow()

    fun getRepositories() {
        viewModelScope.launch {
            repoRepository.getAuthorizedUserRepos().collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _isLoading.emit(true)
                    }

                    is Resource.Success -> {
                        _isLoading.emit(false)
                        resource.data?.let { _repositories.emit(it) }
                    }

                    is Resource.Error -> {
                        _isLoading.emit(false)
                    }
                }
            }
        }
    }
}