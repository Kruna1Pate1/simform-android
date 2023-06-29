package com.krunal.demo.permissionmodel

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.krunal.demo.R
import com.krunal.demo.databinding.ActivityPermissionBinding

class PermissionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPermissionBinding

    private val locationPermissionRequest = getLocationPermissionRequestLauncher()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPermissionBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        setContentView(binding.root)
        setupUI()
    }

    private fun getLocationPermissionRequestLauncher(): ActivityResultLauncher<Array<String>> =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val state = when {
                permissions.getOrDefault(
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION, false
                ) -> "Background with Coarse Location"

                permissions.getOrDefault(
                    Manifest.permission.ACCESS_COARSE_LOCATION, false
                ) -> "Coarse Location"

                else -> "No permissions granted"
            }
            binding.txtPermissionStatus.text = getString(R.string.permission_status, state)
        }

    private fun setupUI() {
        binding.btnLocationPermission.setOnClickListener {
            validatePermission()
        }
    }

    private fun validatePermission() {
        val permissions = buildList {
            add(Manifest.permission.ACCESS_COARSE_LOCATION)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                add(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
            }
        }

        val granted =
            permissions.all { checkSelfPermission(it) == PackageManager.PERMISSION_GRANTED }
        if (granted) {
            binding.txtPermissionStatus.text =
                getString(R.string.permission_status, "Permissions already granted")
        } else {
            locationPermissionRequest.launch(permissions.toTypedArray())
        }
    }
}