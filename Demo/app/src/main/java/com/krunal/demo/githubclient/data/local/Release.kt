package com.krunal.demo.githubclient.data.local

import java.util.Date

data class Release(
    val repoFullName: String,
    val releaseName: String,
    val author: String,
    val authorAvatar: String,
    val releaseDate: Date,
    val releaseAssets: List<ReleaseAsset>
)

data class ReleaseAsset(
    val name: String,
    val size: Int,
    val contentType: String,
    val downloadUrl: String,
)