package com.krunal.demo.githubclient.data.remote.model.request

data class AuthorizationRequest(
    val clientId: String,
    val clientSecret: String,
    val code: String,
    val redirectUri: String? = null,
    val repositoryId: String? = null
)