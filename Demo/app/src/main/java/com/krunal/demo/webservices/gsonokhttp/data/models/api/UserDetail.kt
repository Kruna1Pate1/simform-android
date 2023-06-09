package com.krunal.demo.webservices.gsonokhttp.data.models.api

import com.google.gson.annotations.SerializedName
import java.util.Date

data class UserDetail(
    @SerializedName("id")
    val userId: Int? = null,
    val name: String? = null,
    @SerializedName("avatar")
    val avatarUrl: String? = null,
    val email: String? = null,
    val dob: Date? = null,
    val favoriteMovies: List<Movie> = emptyList()
)