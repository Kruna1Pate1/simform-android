package com.krunal.demo.githubclient.data.remote.model.response

data class AuthorizationResponse(
    val accessToken: String,
    val expiresIn: Int,
    val refreshToken: String,
    val refreshTokenExpiresIn: Int,
    val scope: String,
    val tokenType: String
)