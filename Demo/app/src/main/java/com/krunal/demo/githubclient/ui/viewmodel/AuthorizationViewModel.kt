package com.krunal.demo.githubclient.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.krunal.demo.githubclient.data.remote.model.request.AuthorizationRequest
import com.krunal.demo.githubclient.data.repository.AuthorizationRepository
import com.krunal.demo.githubclient.ui.base.BaseViewModel
import com.krunal.demo.utils.AppConstants
import com.krunal.demo.webservices.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.system.measureTimeMillis

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    private val authorizationRepository: AuthorizationRepository
) : BaseViewModel() {

    private val _authorizationToken = MutableStateFlow<String?>(null)
    val authorizationToken = _authorizationToken.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    fun generateAuthorizationToken(code: String) {
        viewModelScope.launch {
            val body = AuthorizationRequest(
                AppConstants.GITHUB_CLIENT_ID, AppConstants.GITHUB_CLIENT_SECRET, code
            )
            authorizationRepository.getAuthorizationToken(body).collectLatest { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _isLoading.emit(true)
                    }

                    is Resource.Success -> {
                        _isLoading.emit(false)
                        _authorizationToken.emit(resource.data?.accessToken)
                    }

                    is Resource.Error -> {
                        _isLoading.emit(false)
                        resource.message?.let { _errorMessage.emit(it) }
                    }
                }
            }
        }
    }
}