package com.krunal.demo.githubclient.data.remote.api

import com.krunal.demo.githubclient.data.remote.model.response.RepositoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RepoService {

    @GET("user/repos")
    suspend fun getAuthorizedUserRepos(): Response<List<RepositoryResponse>>

    @GET("users/{username}/repos")
    suspend fun getRepos(@Path("username") username: String): Response<List<RepositoryResponse>>
}