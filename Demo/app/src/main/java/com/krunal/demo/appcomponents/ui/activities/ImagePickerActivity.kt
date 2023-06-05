package com.krunal.demo.appcomponents.ui.activities

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.krunal.demo.appcomponents.ui.viewmodels.ImagePickerViewModel
import com.krunal.demo.databinding.ActivityImagePickerBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.File

class ImagePickerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImagePickerBinding
    private val viewModel: ImagePickerViewModel by viewModels()
    private val activityResultLauncher: ActivityResultLauncher<Uri> =
        registerActivityResultLauncher()
    private val uri: Uri by lazy {
        val file = File(Environment.getExternalStorageDirectory().listFiles()[8], "cameraPick")
        FileProvider.getUriForFile(this, "${applicationContext.packageName}.provider", file)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        binding = ActivityImagePickerBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setContentView(binding.root)
        setupUI()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_CAMERA_REQUEST_CODE && grantResults.first() == PackageManager.PERMISSION_GRANTED) {
            takePicture()
        }
    }

    private fun registerActivityResultLauncher(): ActivityResultLauncher<Uri> {
        return registerForActivityResult(ActivityResultContracts.TakePicture()) {
            if (it) viewModel.setImage(uri)
        }
    }

    private fun setupUI() {
        binding.btnPickImage.setOnClickListener {
            if (checkCameraPermission()) {
                takePicture()
            }
        }

        lifecycleScope.launch {
            viewModel.image.collectLatest { uri ->
                binding.imgView.setImageURI(uri)
            }
        }
    }

    private fun takePicture() {
        activityResultLauncher.launch(uri)
    }

    private fun checkCameraPermission(): Boolean {
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.CAMERA), MY_CAMERA_REQUEST_CODE)
            return false
        }
        return true
    }

    companion object {
        private const val MY_CAMERA_REQUEST_CODE = 100
    }
}