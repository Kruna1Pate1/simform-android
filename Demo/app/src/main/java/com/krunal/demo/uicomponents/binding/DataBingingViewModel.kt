package com.krunal.demo.uicomponents.binding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.uicomponents.models.Name
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DataBingingViewModel : ViewModel() {

    private val _nameFlow = MutableStateFlow<Name?>(null)
    val nameFlow: StateFlow<Name?> = _nameFlow

    init {
        randomName()
    }

    /** Generate and emmit random names to [nameFlow] */
    private fun randomName() {
        // List of random names
        val names = listOf(
            Name("Harry", "Potter")
        )

        viewModelScope.launch {
            while (true) {
                delay(3000)
                _nameFlow.emit(names.first())
            }
        }
    }
}