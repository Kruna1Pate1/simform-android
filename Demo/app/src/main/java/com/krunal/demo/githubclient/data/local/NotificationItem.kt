package com.krunal.demo.githubclient.data.local

import androidx.annotation.DrawableRes

enum class NotificationType {
    ISSUE_OPENED, PULL_OPENED, PULL_MERGED, PULL_DRAFT, PULL_CLOSED
}

data class NotificationItem(
    @DrawableRes val icon: Int
)
