package com.krunal.demo.githubclient.data.remote.model.response

data class PullRequest(
    val diffUrl: String,
    val htmlUrl: String,
    val patchUrl: String,
    val url: String
)