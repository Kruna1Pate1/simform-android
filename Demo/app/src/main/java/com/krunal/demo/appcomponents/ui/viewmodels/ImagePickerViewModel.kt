package com.krunal.demo.appcomponents.ui.viewmodels

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ImagePickerViewModel: ViewModel() {

    val image: MutableStateFlow<Uri?> = MutableStateFlow(null)

    fun setImage(image: Uri) {
        viewModelScope.launch {
            this@ImagePickerViewModel.image.emit(image)
        }
    }
}