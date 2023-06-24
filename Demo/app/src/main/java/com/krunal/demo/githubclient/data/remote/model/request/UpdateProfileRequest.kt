package com.krunal.demo.githubclient.data.remote.model.request

data class UpdateProfileRequest(
    val name: String?,
    val email: String?,
    val blog: String?,
    val twitterUsername: String?,
    val company: String?,
    val location: String?,
    val hireable: Boolean?,
    val bio: String?
)