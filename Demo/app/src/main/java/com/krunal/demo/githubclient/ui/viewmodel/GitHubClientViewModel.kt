package com.krunal.demo.githubclient.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.githubclient.data.repository.UserRepository
import com.krunal.demo.webservices.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GitHubClientViewModel @Inject constructor(
    private val apiRepository: UserRepository
) : ViewModel() {

    private var token: String = ""

    fun setToken(token: String) {
        this.token = token
    }

    fun getUser() {
        viewModelScope.launch {
            apiRepository.getAuthorizedUser().collectLatest { resource ->
                when (resource) {
                    is Resource.Loading -> {}
                    is Resource.Error -> {}
                    is Resource.Success -> {}
                }
            }
        }
    }
}