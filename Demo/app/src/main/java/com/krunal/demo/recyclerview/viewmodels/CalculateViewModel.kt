package com.krunal.demo.recyclerview.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.recyclerview.models.Calculation
import com.krunal.demo.recyclerview.models.ContactDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CalculateViewModel : ViewModel() {

    private val _calculations: MutableStateFlow<List<Calculation>> =
        MutableStateFlow(emptyList())
    val calculations: StateFlow<List<Calculation>> = _calculations

    init {
        setupInitialValues()
    }

    private fun setupInitialValues() {
        viewModelScope.launch {
            _calculations.emit(Calculation.dummyData)
        }
    }
}