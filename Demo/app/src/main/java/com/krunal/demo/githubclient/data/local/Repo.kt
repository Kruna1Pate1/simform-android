package com.krunal.demo.githubclient.data.local

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import com.krunal.demo.R
import com.krunal.demo.githubclient.data.remote.model.response.RepositoryResponse
import com.krunal.demo.helpers.ResourceHelper

data class RepoCard(
    val avatar: String, val username: String, val repository: String, val repoFullName: String
)

data class RepoDetail(
    val avatar: String,
    val username: String,
    val repository: String,
    val repoFullName: String,
    val description: String?,
    val website: String?,
    val starCount: Int,
    val watchCount: Int?,
    val forkCount: Int?,
    val repoItems: List<RepoDetailItem> = emptyList()
)

data class RepoDetailItem(
    @DrawableRes val icon: Int,
    @ColorInt val iconBackground: Int,
    val title: String,
    val count: Int? = null
) {
    companion object {
        fun from(repositoryResponse: RepositoryResponse) = with(repositoryResponse) {
            with(ResourceHelper.resources) {
                listOf(
                    RepoDetailItem(
                        R.drawable.ic_git_pull_request,
                        getColor(R.color.github_pull, null),
                        getString(R.string.issues),
                    ),
                    RepoDetailItem(
                        R.drawable.ic_git_pull_request,
                        getColor(R.color.github_pull, null),
                        getString(R.string.releases)
                    ),
                )
            }
        }
    }
}
