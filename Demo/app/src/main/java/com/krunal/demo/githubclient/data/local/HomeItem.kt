package com.krunal.demo.githubclient.data.local

import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes

sealed interface HomeItem {
    val type: Type

    data class Heading(
        val title: String,
        override val type: Type = Type.HEADING
    ): HomeItem

    data class WorkItem(
        @DrawableRes val icon: Int,
        @ColorInt val iconBackground: Int,
        val title: String,
        override val type: Type = Type.WORK
    ): HomeItem

    data class Favorite(
        val image: Drawable,
        val organization: String,
        val repoName: String,
        override val type: Type = Type.FAVORITES
    ): HomeItem

    data class Recent(
        @DrawableRes val icon: Int,
        val repo: String,
        val title: String,
        val authorAvatar: String,
        val description: String,
        override val type: Type = Type.RECENT
    ): HomeItem

    enum class Type {
        HEADING,
        WORK,
        FAVORITES,
        RECENT
    }
}