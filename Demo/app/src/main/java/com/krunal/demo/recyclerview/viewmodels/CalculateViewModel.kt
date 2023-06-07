package com.krunal.demo.recyclerview.viewmodels

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.recyclerview.models.Calculation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CalculateViewModel : ViewModel() {

    private val _calculations: MutableStateFlow<List<Calculation>> = MutableStateFlow(emptyList())
    val calculations: StateFlow<List<Calculation>> = _calculations

    init {
        setupInitialValues()
    }

    private fun setupInitialValues() {
        viewModelScope.launch {
            _calculations.emit(Calculation.dummyData)
        }
    }

    fun updateCalculationList(calculations: List<Calculation>) {
        viewModelScope.launch {
            _calculations.emit(calculations)
        }
    }

    fun updateNumber(position: Int, num1: Double?, num2: Double?) {
        viewModelScope.launch {
            val list = calculations.value.toMutableList()
            if (num1 != null ) {
                list[position] = list[position].copy(num1 = num1)
            } else if (num2 != null) {
                list[position] = list[position].copy(num2 = num2)
            }
            _calculations.emit(list)
        }
    }

    fun addValue(position: Int, value: Int) {
        viewModelScope.launch {
            val list = calculations.value.toMutableList()
            list[position] =
                list[position].copy(additionalNums = list[position].additionalNums + value)
            _calculations.emit(list)
        }
    }

    fun removeValue(position: Int, valuePosition: Int) {
        viewModelScope.launch {
            val list = calculations.value.toMutableList()
            list[position] = list[position].copy(
                additionalNums = list[position].additionalNums - list[position].additionalNums[valuePosition]
            )
            _calculations.emit(list)
        }
    }

    fun updateValue(position: Int, valuePosition: Int, value: Int) {
        viewModelScope.launch {
            val list = calculations.value.toMutableList()
            val additionalNums = list[position].additionalNums.toMutableList()
            additionalNums[valuePosition] = value
            list[position] = list[position].copy(additionalNums = additionalNums)
            _calculations.emit(list)
        }
    }

    fun addCalculation() {
        viewModelScope.launch {
            calculations.value.toMutableList().also { list ->
                list.add(
                    Calculation(0.0, 0.0)
                )
                _calculations.emit(list)
            }
        }
    }

    fun removeCalculation(position: Int) {
        viewModelScope.launch {
            calculations.value.toMutableList().also { list ->
                list.removeAt(position)
                _calculations.emit(list)
            }
        }
    }

    fun addImage(position: Int, uri: Uri) {
        viewModelScope.launch {
            val list = calculations.value.toMutableList()
            list[position] = list[position].copy(images = list[position].images + uri)
            _calculations.emit(list)
        }
    }

    fun removeImage(position: Int, imagePosition: Int) {
        viewModelScope.launch {
            val list = calculations.value.toMutableList()
            list[position] = list[position].copy(
                images = list[position].images - list[position].images[imagePosition]
            )
            _calculations.emit(list)
        }
    }
}