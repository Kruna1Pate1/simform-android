package com.krunal.demo.recyclerview.helpers

import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity

class ImagePickerHelper(private val activity: FragmentActivity) {

    private val activityResultLauncher: ActivityResultLauncher<String> =
        registerActivityResultLauncher()
    private var onImagePick: ((uri: Uri) -> Unit)? = null

    private fun registerActivityResultLauncher(): ActivityResultLauncher<String> {
        return activity.registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                onImagePick?.invoke(uri)
            }
//            uri?.let(onImagePick)
        }
    }

    fun pickImage(onPick: (uri: Uri) -> Unit) {
        onImagePick = onPick
        activityResultLauncher.launch(ANY_IMAGE_TYPE)
    }

    companion object {

        const val IMAGE_TYPE = "image"
        const val ANY_IMAGE_TYPE = "$IMAGE_TYPE/*"
    }
}