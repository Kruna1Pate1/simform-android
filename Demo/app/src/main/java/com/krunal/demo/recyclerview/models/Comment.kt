package com.krunal.demo.recyclerview.models

import androidx.annotation.DrawableRes
import com.krunal.demo.R

data class Comment(
    val name: String, @DrawableRes val profileImage: Int, val text: String, val uploadDate: String
) {
    companion object {
        val dummyComments: List<Comment> = buildList {
            repeat(4) {
                add(
                    Comment(
                        "Movie Snaps", R.drawable.android_dev, "Great song", "10 days ago"
                    )
                )
                add(
                    Comment(
                        "Nossen Kanter Snaps", R.drawable.profile, "0:23 best part", "1 week ago"
                    )
                )
            }
        }
    }
}
