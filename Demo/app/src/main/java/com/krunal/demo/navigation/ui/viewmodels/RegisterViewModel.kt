package com.krunal.demo.navigation.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.krunal.demo.R
import com.krunal.demo.navigation.data.models.Credential
import com.krunal.demo.navigation.data.models.RegisterDetail
import com.krunal.demo.navigation.data.models.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow

class RegisterViewModel : ViewModel() {

    val registerDetail: MutableStateFlow<RegisterDetail> =
        MutableStateFlow(RegisterDetail())

}