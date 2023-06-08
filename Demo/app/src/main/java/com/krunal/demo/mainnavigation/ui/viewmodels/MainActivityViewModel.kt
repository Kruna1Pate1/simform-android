package com.krunal.demo.mainnavigation.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.mainnavigation.data.models.ComponentDetail
import com.krunal.demo.mainnavigation.data.repositories.ComponentDetailRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val _componentDetails = MutableStateFlow<List<ComponentDetail>>(emptyList())
    val componentDetails: StateFlow<List<ComponentDetail>> = _componentDetails

    var isHomeComponents = true

    init {
        setupInitialData()
    }

    private fun setupInitialData() {
        homeComponents()
    }

    fun changeComponentDetails(componentDetails: List<ComponentDetail>) {
        isHomeComponents = false
        viewModelScope.launch {
            _componentDetails.emit(componentDetails)
        }
    }

    fun homeComponents() {
        isHomeComponents = true
        viewModelScope.launch {
            _componentDetails.emit(ComponentDetailRepository.getHomeComponents())
        }
    }
}