package com.krunal.demo.githubclient.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.githubclient.data.local.HomeItem
import com.krunal.demo.githubclient.data.repository.HomeRepository
import com.krunal.demo.githubclient.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GitHubHomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _homeItems = MutableStateFlow<List<HomeItem>>(emptyList())
    val homeItem = _homeItems.asStateFlow()

    fun getItems() {
        viewModelScope.launch {
            _homeItems.emit(emptyList())
        }
        getWorkItems()
    }

    private fun getWorkItems() {
        viewModelScope.launch {
            homeRepository.getWorkItems().collectLatest {
                _homeItems.emit(
                    homeItem.value + it
                )
            }
        }
    }
}