package com.krunal.demo.recyclerview.models

import androidx.annotation.DrawableRes
import com.krunal.demo.R

data class VideoDetails(
    val title: String,
    val time: Long,
    @DrawableRes val thumbnail: Int,
    val author: String,
    val views: String,
    val uploadDate: String,
    @DrawableRes val profileImage: Int,
    val type: FeedType
) {

    companion object {
        val dummyData: List<VideoDetails>
            get() = listOf(
                VideoDetails(
                    "Running Up That Hill (Kate Bush)",
                    4000,
                    R.drawable.running_up_that_hill,
                    "Netflix",
                    "2.5M views",
                    "11 months ago",
                    R.drawable.netflix_logo,
                    FeedType.VIDEO
                ),
                VideoDetails(
                    "Running Up That Hill (Kate Bush)",
                    4000,
                    R.drawable.running_up_that_hill,
                    "Netflix",
                    "2.5M views",
                    "11 months ago",
                    R.drawable.netflix_logo,
                    FeedType.SHORT_VIDEO
                )
            )
    }
}
