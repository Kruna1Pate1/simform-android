package com.krunal.demo.githubclient.data.local

import androidx.annotation.DrawableRes

enum class NotificationType {
    ISSUE_OPENED, PULL_OPENED, PULL_MERGED, PULL_DRAFT, PULL_CLOSED
}

data class NotificationItem(
    @DrawableRes val icon: Int,
    val repoName: String,
    val issueId: Int,
    val title: String,
    val isUnread: Boolean
) {

    val heading: String
        get() = "$repoName #$issueId"
}
