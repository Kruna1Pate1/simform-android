package com.krunal.demo.githubclient.data.remote.model.request

data class IssueRequest(
    val title: String,
    val body: String? = null,
    val milestone: String? = null,
    val labels: List<String> = emptyList(),
    val assignees: List<String> = emptyList()
)