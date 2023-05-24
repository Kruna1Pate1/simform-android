package com.krunal.demo.recyclerview.models

import androidx.annotation.DrawableRes
import com.krunal.demo.R

sealed interface Feed {
    val type: FeedType

    companion object {
        val dummyData: List<Feed>
            get() = buildList {
                repeat(10) {
                    add(
                        VideoDetails(
                            "Running Up That Hill (Kate Bush)",
                            4000,
                            R.drawable.running_up_that_hill,
                            "Netflix",
                            "2.5M views",
                            "11 months ago",
                            R.drawable.netflix_logo,
                            FeedType.VIDEO
                        )
                    )
                    add(
                        VideoDetails(
                            "Kaise Ab Kahein | Gutar Gu",
                            134,
                            R.drawable.thumbnail1,
                            "Amazon miniTV",
                            "430K views",
                            "1 months ago",
                            R.drawable.netflix_logo,
                            FeedType.VIDEO
                        )
                    )
                    add(
                        VideoDetails(
                            "Running Up That Hill (Kate Bush)",
                            4000,
                            R.drawable.running_up_that_hill,
                            "Netflix",
                            "2.5M views",
                            "11 months ago",
                            R.drawable.netflix_logo,
                            FeedType.VIDEO
                        )
                    )
                    add(
                        CommunityPost(
                            "Android Developers",
                            """
                                🎪 Coming to you live from Shoreline, it's #GoogleIO!

                                Join developers around the world for thoughtful discussions, interactive learning, and a first look at our latest product updates. 
                            """.trimIndent(),
                            R.drawable.android_dev,
                            "12 days ago",
                            235,
                            -1,
                            listOf(
                                R.drawable.google_io,
                                R.drawable.google_io
                            )
                        )
                    )
                    add(
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
    }
}

data class VideoDetails(
    val title: String,
    val time: Long,
    @DrawableRes val thumbnail: Int,
    val author: String,
    val views: String,
    val uploadDate: String,
    @DrawableRes val profileImage: Int,
    override val type: FeedType
) : Feed

data class CommunityPost(
    val name: String,
    val description: String?,
    @DrawableRes val profileImage: Int,
    val uploadDate: String,
    val likes: Int,
    val dislikes: Int,
    val images: List<Int> = emptyList(),
    val comments: List<String> = emptyList(),
    override val type: FeedType = FeedType.COMMUNITY_POST
) : Feed

data class Recommendation(
    val name: String,
    val videos: List<VideoDetails>
)
