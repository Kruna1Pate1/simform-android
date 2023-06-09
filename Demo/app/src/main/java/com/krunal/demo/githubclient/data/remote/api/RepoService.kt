package com.krunal.demo.githubclient.data.remote.api

import com.krunal.demo.githubclient.data.local.Release
import com.krunal.demo.githubclient.data.remote.model.response.ReleaseResponse
import com.krunal.demo.githubclient.data.remote.model.response.RepositoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface RepoService {

    @GET("user/repos")
    suspend fun getAuthorizedUserRepos(): Response<List<RepositoryResponse>>

    @GET("users/{username}/repos")
    suspend fun getRepos(@Path("username") username: String): Response<List<RepositoryResponse>>

    @PUT("repos/{repo}/contents/{path}")
    suspend fun createFile(@Path("repo", encoded = true) repo: String, @Path("path") filePath: String): Response<List<RepositoryResponse>>

    @GET("repos/{repoName}")
    suspend fun getRepo(@Path("repoName", encoded = true) repoName: String): Response<RepositoryResponse>

    @GET("repos/{repoName}/releases/latest")
    suspend fun getLatestRelease(@Path("repoName", encoded = true) repoName: String): Response<ReleaseResponse>
}