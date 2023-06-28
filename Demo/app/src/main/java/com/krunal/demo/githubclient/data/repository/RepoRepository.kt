package com.krunal.demo.githubclient.data.repository

import com.krunal.demo.githubclient.data.local.DownloadState
import com.krunal.demo.githubclient.data.local.Release
import com.krunal.demo.githubclient.data.local.ReleaseAsset
import com.krunal.demo.githubclient.data.local.RepoCard
import com.krunal.demo.githubclient.data.local.RepoDetail
import com.krunal.demo.githubclient.data.local.RepoDetailItem
import com.krunal.demo.githubclient.data.remote.api.FileService
import com.krunal.demo.githubclient.data.remote.api.RepoService
import com.krunal.demo.githubclient.extentions.saveFile
import com.krunal.demo.githubclient.ui.base.BaseRepository
import com.krunal.demo.webservices.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.File

class RepoRepository(private val repoService: RepoService, private val fileService: FileService) :
    BaseRepository() {

    suspend fun getAuthorizedUserRepos() = flow<Resource<List<RepoCard>>> {
        emit(Resource.Loading())
        repoService.getAuthorizedUserRepos().let { response ->
            val resource = handleResponse(response)
            val data = resource.data?.map {
                RepoCard(
                    it.owner.avatarUrl, it.owner.username, it.name, it.fullName
                )
            }
            if (resource is Resource.Success) {
                emit(Resource.Success(data))
            } else if (resource is Resource.Error) {
                emit(Resource.Error(resource.message ?: "Can't fetch repos", data))
            }
        }
    }.flowOn(Dispatchers.IO)


    suspend fun getRepos(username: String) = flow<Resource<List<RepoCard>>> {
        emit(Resource.Loading())
        repoService.getRepos(username).let { response ->
            val resource = handleResponse(response)
            val data = resource.data?.map {
                RepoCard(
                    it.owner.avatarUrl, it.owner.username, it.name, it.fullName
                )
            }
            if (resource is Resource.Success) {
                emit(Resource.Success(data))
            } else if (resource is Resource.Error) {
                emit(Resource.Error(resource.message ?: "Can't fetch repos", data))
            }
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getRepo(repoName: String) = flow<Resource<RepoDetail>> {
        emit(Resource.Loading())
        repoService.getRepo(repoName).let { response ->
            val resource = handleResponse(response)
            if (resource is Resource.Success) {
                resource.data?.let {
                    val detail = RepoDetail(
                        it.owner.avatarUrl,
                        it.owner.username,
                        it.name,
                        it.fullName,
                        it.description,
                        it.owner.blog,
                        it.stargazersCount,
                        it.watchersCount,
                        it.forksCount,
                        RepoDetailItem.from(it)
                    )
                    emit(Resource.Success(detail))
                }
            }
        }
    }.flowOn(Dispatchers.IO)

    fun getLatestReleaseAssets(repoName: String) = flow<Resource<Release>> {
        repoService.getLatestRelease(repoName).let { response ->
            val resource = handleResponse(response)
            val data = resource.data?.let { release ->
                val assets = release.assets.map {
                    ReleaseAsset(it.name, it.size, it.contentType, it.browserDownloadUrl)
                }
                Release(
                    repoName,
                    release.name,
                    release.author.username,
                    release.author.avatarUrl,
                    release.publishedAt,
                    assets
                )
            }

            if (resource is Resource.Success) {
                emit(Resource.Success(data))
            } else if (resource is Resource.Error) {
                emit(Resource.Error(resource.message ?: "Can't fetch assets", data))
            }
        }
    }

    suspend fun downloadAsset(url: String, file: File): Flow<DownloadState> =
        fileService.downloadFile(url).saveFile(file)
}