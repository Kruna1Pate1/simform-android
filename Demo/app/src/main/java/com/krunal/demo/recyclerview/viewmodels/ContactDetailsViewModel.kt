package com.krunal.demo.recyclerview.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.recyclerview.models.ContactDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ContactDetailsViewModel : ViewModel() {

    private val _contactDetails: MutableStateFlow<List<ContactDetail>> =
        MutableStateFlow(emptyList())
    val contactDetail: StateFlow<List<ContactDetail>> = _contactDetails

    init {
        setupInitialValues()
    }

    private fun setupInitialValues() {
        viewModelScope.launch {
            _contactDetails.emit(ContactDetail.dummyData)
        }
    }
}