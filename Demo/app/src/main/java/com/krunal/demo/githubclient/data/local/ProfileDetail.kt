package com.krunal.demo.githubclient.data.local

import androidx.annotation.DrawableRes
import com.krunal.demo.R
import com.krunal.demo.githubclient.data.remote.model.response.UserResponse

enum class DetailType {
    NAME, INFO
}

data class Profile(
    val items: List<ProfileCard>
)

sealed interface ProfileDetail {

    val type: DetailType

    data class ProfileName(
        val avatar: String, val username: String, val name: String? = null,
        override val type: DetailType= DetailType.NAME
    ) : ProfileDetail

    data class ProfileInfo(
        @DrawableRes val icon: Int, val title: String, override val type: DetailType = DetailType.INFO
    ) : ProfileDetail {
        companion object {

            fun from(userResponse: UserResponse) = buildList {
                with(userResponse) {
                    bio?.let {
                        add(ProfileInfo(R.drawable.ic_info, it))
                    }
                    company?.let {
                        add(ProfileInfo(R.drawable.ic_chat, it))
                    }
                    blog?.let {
                        add(ProfileInfo(R.drawable.ic_link_logo, it))
                    }
                    email?.let {
                        add(ProfileInfo(R.drawable.ic_email, it))
                    }
                }
            }
        }
    }
}

data class ProfileCard(
    val profileDetail: List<ProfileDetail>
) {
    companion object {

        fun from(userResponse: UserResponse): ProfileCard {
            val profileName = with(userResponse) {
                ProfileDetail.ProfileName(avatarUrl, username, name)
            }
            val profileDetails: MutableList<ProfileDetail> = ProfileDetail.ProfileInfo.from(userResponse).toMutableList()
            profileDetails.add(0, profileName)
            return ProfileCard(profileDetails)
        }
    }
}