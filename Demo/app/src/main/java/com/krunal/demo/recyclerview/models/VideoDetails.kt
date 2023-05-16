package com.krunal.demo.recyclerview.models

import androidx.annotation.DrawableRes

data class VideoDetails(
    val title: String,
    val time: Long,
    @DrawableRes val thumbnail: Int,
    val author: String,
    val views: String,
    val uploadDate: String,
    @DrawableRes val profileImage: Int,
)
