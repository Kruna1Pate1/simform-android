package com.krunal.demo.webservices.gsonokhttp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.webservices.gsonokhttp.data.models.api.UserDetail
import com.krunal.demo.webservices.gsonokhttp.repositories.UserRepository
import com.krunal.demo.webservices.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val _users: MutableStateFlow<List<UserDetail>> = MutableStateFlow(emptyList())
    val users = _users

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {

            UserRepository.getAllUsers().collectLatest { response ->
                when (response) {
                    is Resource.Loading -> {

                        _isLoading.emit(true)
                    }

                    is Resource.Success -> {
                        response.data?.let { _users.emit(it) }
                        _isLoading.emit(true)
                    }

                    is Resource.Error -> {
                        _isLoading.emit(true)
                    }
                }
            }

        }
    }
}