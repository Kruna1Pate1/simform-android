package com.krunal.demo.githubclient.data.repository

import com.krunal.demo.R
import com.krunal.demo.githubclient.data.local.HomeItem
import com.krunal.demo.helpers.ResourceHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class HomeRepository {

    fun getWorkItems() = flow<List<HomeItem>> {
        val list = with(ResourceHelper.resources) {
            listOf(
                HomeItem.Heading(getString(R.string.my_work)),
                HomeItem.WorkItem(
                    R.drawable.ic_issue_opened_24,
                    getColor(R.color.github_issues, null),
                    getString(R.string.issues)
                ), HomeItem.WorkItem(
                    R.drawable.ic_git_pull_request,
                    getColor(R.color.github_pull, null),
                    getString(R.string.pull_requests)
                ), HomeItem.WorkItem(
                    R.drawable.ic_discussion_closed_24,
                    getColor(R.color.github_discussion, null),
                    getString(R.string.discussion)
                ), HomeItem.WorkItem(
                    R.drawable.ic_repo_24,
                    getColor(R.color.github_repository, null),
                    getString(R.string.repositories)
                ), HomeItem.WorkItem(
                    R.drawable.ic_organization_24,
                    getColor(R.color.github_organization, null),
                    getString(R.string.organizations)
                ), HomeItem.WorkItem(
                    R.drawable.ic_star_24,
                    getColor(R.color.github_starred, null),
                    getString(R.string.starred)
                )
            )
        }
        emit(list)
    }.flowOn(Dispatchers.IO)
}