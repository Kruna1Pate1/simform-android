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

    fun updateCalculation(calculations: List<Calculation>) {
        viewModelScope.launch {
            _calculations.emit(calculations)
        }
    }

    fun addValue(position: Int, value: Int) {
        viewModelScope.launch {
            val list = _calculations.value.toMutableList()
            list[position].additionalNums.add(value)
            _calculations.emit(list)
        }
    }

    fun removeValue(position: Int, valuePosition: Int) {
        viewModelScope.launch {
            val list = _calculations.value.toMutableList()
            list[position].additionalNums.removeAt(position)
            _calculations.emit(list)
        }
    }

    fun updateValue(position: Int, valuePosition: Int, value: Int) {
        viewModelScope.launch {
            val list = _calculations.value.toMutableList()
            list[position].additionalNums[valuePosition] = value
            _calculations.emit(list)
        }
    }
}