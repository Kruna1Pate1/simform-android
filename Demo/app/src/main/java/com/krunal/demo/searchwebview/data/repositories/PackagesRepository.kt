package com.krunal.demo.searchwebview.data.repositories

import com.krunal.demo.searchwebview.data.models.PackageDetail
import com.krunal.demo.searchwebview.helpers.PackageHelper

object PackagesRepository {

    fun getPackageDetails(): List<PackageDetail> = PackageHelper.getPackageDetails()
}