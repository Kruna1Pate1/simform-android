package com.krunal.demo.githubclient.data.local

import androidx.annotation.DrawableRes
import com.krunal.demo.R
import com.krunal.demo.githubclient.data.remote.model.response.UserResponse

data class Profile(
    val items: List<ProfileCard>
)

sealed interface ProfileDetail {

    data class ProfileName(
        val avatar: String, val username: String, val name: String? = null
    ) : ProfileDetail

    data class ProfileInfo(
        @DrawableRes val icon: Int, val title: String
    ) : ProfileDetail {
        companion object {

            fun from(userResponse: UserResponse) = buildList<ProfileInfo> {
                userResponse.bio?.let {
                    add(ProfileInfo(R.drawable.ic_info, it))
                }
                userResponse.company?.let {
                    add(ProfileInfo(R.drawable.ic_chat, it))
                }
                userResponse.blog?.let {
                    add(ProfileInfo(R.drawable.ic_link_logo, it))
                }
                userResponse.email?.let {
                    add(ProfileInfo(R.drawable.ic_email, it))
                }
            }
        }
    }
}

data class ProfileCard(
    val profileDetail: List<ProfileDetail>
) {
    companion object {
        fun from(userResponse: UserResponse) {
            buildList<ProfileDetail> {
                with(userResponse) {
                    add(ProfileDetail.ProfileName(avatarUrl, username, name))
                    bio?.let {
                        add(ProfileDetail.ProfileInfo(R.drawable.ic_info, it))
                    }
                    company?.let {
                        add(ProfileDetail.ProfileInfo(R.drawable.ic_chat, it))
                    }
                    blog?.let {
                        add(ProfileDetail.ProfileInfo(R.drawable.ic_link_logo, it))
                    }
                    email?.let {
                        add(ProfileDetail.ProfileInfo(R.drawable.ic_email, it))
                    }
                }
            }
        }
    }
}