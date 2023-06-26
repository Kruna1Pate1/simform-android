package com.krunal.demo.githubclient.data.repository

import com.krunal.demo.githubclient.data.local.RepoCard
import com.krunal.demo.githubclient.data.remote.api.RepoService
import com.krunal.demo.githubclient.ui.base.BaseRepository
import com.krunal.demo.webservices.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RepoRepository(private val repoService: RepoService) : BaseRepository() {

    suspend fun getAuthorizedUserRepos() = flow<Resource<List<RepoCard>>> {
        emit(Resource.Loading())
        repoService.getAuthorizedUserRepos().let { response ->
            val resource = handleResponse(response)
            val data =
                resource.data?.map { RepoCard(it.owner.avatarUrl, it.owner.username, it.name, it.fullName) }
            if (resource is Resource.Success) {
                emit(Resource.Success(data))
            } else if (resource is Resource.Error) {
                emit(Resource.Error(resource.message ?: "Can't fetch repos", data))
            }
        }
    }.flowOn(Dispatchers.IO)
}