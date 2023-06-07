package com.krunal.demo.searchwebview.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.searchwebview.data.models.PackageDetail
import com.krunal.demo.searchwebview.data.repositories.PackagesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val _suggestions: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    val suggestions: StateFlow<List<String>> = _suggestions

    private val _packageDetails: MutableStateFlow<List<PackageDetail>> = MutableStateFlow(emptyList())
    val packageDetails: StateFlow<List<PackageDetail>> = _packageDetails

    init {
        setupInitialData()
    }

    private fun setupInitialData() {
        viewModelScope.launch {
            _packageDetails.emit(PackagesRepository.getPackageDetails())
        }
    }
}