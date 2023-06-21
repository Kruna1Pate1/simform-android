package com.krunal.demo.githubclient.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.githubclient.data.local.Profile
import com.krunal.demo.githubclient.data.local.ProfileDetail
import com.krunal.demo.githubclient.data.repository.ApiRepository
import com.krunal.demo.webservices.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val apiRepository: ApiRepository
) : ViewModel() {

    private var token: String = ""

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _profileDetail = MutableStateFlow<Profile?>(null)
    val profileDetail = _profileDetail.asStateFlow()

    fun setToken(token: String) {
        this.token = token
    }

    fun getUser() {
        viewModelScope.launch {
            apiRepository.getAuthorizedUser().collectLatest { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _isLoading.emit(true)
                    }

                    is Resource.Success -> {
                        _isLoading.emit(false)
                        _profileDetail.emit(resource.data)
                    }

                    is Resource.Error -> {
                        _isLoading.emit(false)
                    }
                }
            }
        }
    }
}