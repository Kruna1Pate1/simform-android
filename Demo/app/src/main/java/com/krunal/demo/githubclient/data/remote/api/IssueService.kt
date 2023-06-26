package com.krunal.demo.githubclient.data.remote.api

import com.krunal.demo.githubclient.data.remote.model.request.IssueRequest
import com.krunal.demo.githubclient.data.remote.model.response.IssueResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface IssueService {

    @POST("repos/{repo}/issues")
    suspend fun createIssue(@Path("repo", encoded = true) repo: String, @Body issue: IssueRequest): Response<IssueResponse>

    @PATCH("repos/{repo}/issues")
    suspend fun updateIssue(@Path("repo", encoded = true) repo: String, @Body issue: IssueRequest): Response<IssueResponse>
}