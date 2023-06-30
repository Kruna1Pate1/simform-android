package com.krunal.demo.githubclient.data.remote.model.response

data class AuthorizationErrorResponse(
    val error: String,
    val errorDescription: String,
    val errorUri: String
)

data class ApiError(
    val documentationUrl: String,
    val message: String
)