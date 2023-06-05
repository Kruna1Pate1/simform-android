package com.krunal.demo.navigation.data.models

data class UserProfile(
    val name: String,
    val credential: Credential,
    val rank: Int = -1,
    val wins: Int = 0,
    val losses: Int = 0,
    val pts: Int = 0
)
