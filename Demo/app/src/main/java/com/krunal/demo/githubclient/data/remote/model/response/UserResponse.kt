package com.krunal.demo.githubclient.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("login")
    val username: String,
    val id: Int,
    val nodeId: String,
    val avatarUrl: String,
    val gravatarId: String?,
    val htmlUrl: String,
    val url: String,
    val followersUrl: String,
    val followingUrl: String,
    val bio: String?,
    val blog: String?,
    val company: String?,
    val createdAt: String,
    val email: String,
    val eventsUrl: String,
    val followers: Int,
    val following: Int,
    val gistsUrl: String,
    val hireable: Boolean?,
    val location: String?,
    val name: String?,
    val organizationsUrl: String,
    val publicGists: Int,
    val publicRepos: Int,
    val receivedEventsUrl: String,
    val reposUrl: String,
    val siteAdmin: Boolean,
    val starredUrl: String,
    val subscriptionsUrl: String,
    val twitterUsername: String?,
    val type: String,
    val updatedAt: String
)