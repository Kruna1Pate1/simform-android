package com.krunal.demo.githubclient.data.remote.model.response

data class IssueResponse(
    val activeLockReason: String,
    val assignee: UserResponse,
    val assignees: List<UserResponse>,
    val authorAssociation: String,
    val body: String,
    val closedAt: Any?,
    val closedBy: UserResponse,
    val comments: Int,
    val commentsUrl: String,
    val createdAt: String,
    val eventsUrl: String,
    val htmlUrl: String,
    val id: Int,
    val labels: List<Label>,
    val labelsUrl: String,
    val locked: Boolean,
    val milestone: Milestone,
    val nodeId: String,
    val number: Int,
    val pullRequest: PullRequest,
    val repositoryUrl: String,
    val state: String,
    val stateReason: String,
    val title: String,
    val updatedAt: String,
    val url: String,
    val user: UserResponse
)

data class Label(
    val color: String,
    val default: Boolean,
    val description: String,
    val id: String,
    val name: String,
    val nodeId: String,
    val url: String
)

data class Milestone(
    val closedAt: String,
    val closedIssues: Int,
    val createdAt: String,
    val creator: UserResponse,
    val description: String,
    val dueOn: String,
    val htmlUrl: String,
    val id: Int,
    val labelsUrl: String,
    val nodeId: String,
    val number: Int,
    val openIssues: Int,
    val state: String,
    val title: String,
    val updatedAt: String,
    val url: String
)