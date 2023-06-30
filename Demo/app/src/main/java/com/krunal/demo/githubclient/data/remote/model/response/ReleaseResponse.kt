package com.krunal.demo.githubclient.data.remote.model.response

import java.util.Date

data class ReleaseResponse(
    val assets: List<Asset>,
    val assetsUrl: String,
    val author: UserResponse,
    val body: String,
    val createdAt: Date,
    val draft: Boolean,
    val htmlUrl: String,
    val id: Int,
    val mentionsCount: Int,
    val name: String,
    val nodeId: String,
    val prerelease: Boolean,
    val publishedAt: Date,
    val tagName: String,
    val tarballUrl: String,
    val targetCommitish: String,
    val uploadUrl: String,
    val url: String,
    val zipballUrl: String
)

data class Asset(
    val browserDownloadUrl: String,
    val contentType: String,
    val createdAt: Date,
    val downloadCount: Int,
    val id: Int,
    val label: Any?,
    val name: String,
    val nodeId: String,
    val size: Int,
    val state: String,
    val updatedAt: Date,
    val uploader: UserResponse,
    val url: String
)
