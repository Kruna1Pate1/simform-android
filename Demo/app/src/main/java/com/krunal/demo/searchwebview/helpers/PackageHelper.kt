package com.krunal.demo.searchwebview.helpers

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import com.krunal.demo.searchwebview.data.models.PackageDetail

object PackageHelper {

    private lateinit var packageManager: PackageManager

    private val packages: List<PackageInfo> by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            packageManager.getInstalledPackages(PackageManager.PackageInfoFlags.of(PackageManager.GET_META_DATA.toLong()))
        } else {
            @Suppress("DEPRECATION") packageManager.getInstalledPackages(PackageManager.GET_META_DATA)
        }
    }

    /**
     * set the context that is being used to access the shared preferences
     */
    fun initialize(context: Context) {

        packageManager = context.packageManager
    }

    fun getPackageDetails(): List<PackageDetail> = packages.map {
        PackageDetail(
            name = it.applicationInfo.loadLabel(
                packageManager
            ).toString(),
            packageName = it.packageName,
            icon = it.applicationInfo.loadIcon(packageManager)
        )
    }
}