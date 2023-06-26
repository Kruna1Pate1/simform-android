package com.krunal.demo.githubclient.data.repository

import com.krunal.demo.githubclient.data.local.IssueModel
import com.krunal.demo.githubclient.data.remote.api.IssueService
import com.krunal.demo.githubclient.data.remote.model.request.IssueRequest
import com.krunal.demo.githubclient.data.remote.model.response.IssueResponse
import com.krunal.demo.githubclient.ui.base.BaseRepository
import com.krunal.demo.webservices.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class IssueRepository(private val issueService: IssueService) : BaseRepository() {

    // TODO: List Issue

    suspend fun createIssue(repo: String, issueModel: IssueModel) = flow<Resource<IssueResponse>> {
        emit(Resource.Loading())
        val issue = IssueRequest(issueModel.title, issueModel.body)
        issueService.createIssue(repo, issue).let { response ->
            val resource = handleResponse(response)
            emit(resource)
        }
    }.flowOn(Dispatchers.IO)

    suspend fun updateIssue(repo: String, issueModel: IssueModel) = flow<Resource<IssueResponse>> {
        emit(Resource.Loading())
        val issue = IssueRequest(issueModel.title, issueModel.body)
        issueService.updateIssue(repo, issue).let { response ->
            val resource = handleResponse(response)
            emit(resource)
        }
    }.flowOn(Dispatchers.IO)
}