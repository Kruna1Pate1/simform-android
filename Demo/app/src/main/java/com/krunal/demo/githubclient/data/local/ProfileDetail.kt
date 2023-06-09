package com.krunal.demo.githubclient.data.local

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import com.krunal.demo.R
import com.krunal.demo.githubclient.data.remote.model.response.UserResponse
import com.krunal.demo.helpers.ResourceHelper

enum class DetailType {
    NAME, INFO
}

enum class ProfileType {
    DETAIL, ITEM
}

data class ProfileModel(
    val items: List<ProfileInfo>
)

sealed interface ProfileDetail {

    val type: DetailType

    data class ProfileName(
        val avatar: String,
        val username: String,
        val name: String? = null,
        override val type: DetailType = DetailType.NAME
    ) : ProfileDetail

    data class ProfileInfo(
        @DrawableRes val icon: Int,
        val title: String,
        override val type: DetailType = DetailType.INFO
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
                    add(
                        ProfileInfo(
                            R.drawable.ic_person_24,
                            ResourceHelper.resources.getString(R.string.follow_info, followers, following)
                        )
                    )
                }
            }
        }
    }
}

sealed interface ProfileInfo {
    val type: ProfileType

    data class ProfileCard(
        val profileDetail: List<ProfileDetail>,
        override val type: ProfileType = ProfileType.DETAIL
    ) : ProfileInfo {
        companion object {

            fun from(userResponse: UserResponse): ProfileCard {
                val profileName = with(userResponse) {
                    ProfileDetail.ProfileName(avatarUrl, username, name)
                }
                val profileDetails: MutableList<ProfileDetail> =
                    ProfileDetail.ProfileInfo.from(userResponse).toMutableList()
                profileDetails.add(0, profileName)
                return ProfileCard(profileDetails)
            }
        }
    }

    data class ProfileItem(
        @DrawableRes val icon: Int,
        @ColorInt val iconBackground: Int,
        val title: String,
        val count: Int? = null,
        override val type: ProfileType = ProfileType.ITEM
    ) : ProfileInfo {

        companion object {
            fun from(userResponse: UserResponse) = with(userResponse) {
                with(ResourceHelper.resources) {
                listOf(
                    ProfileItem(
                        R.drawable.ic_repo_24,
                        getColor(R.color.github_pull, null),
                        getString(R.string.repositories),
                        publicRepos
                    ), ProfileItem(
                        R.drawable.ic_git_pull_request,
                        getColor(R.color.github_gists, null),
                        getString(R.string.gists),
                        publicGists
                    ), ProfileItem(
                        R.drawable.ic_organization_24,
                        getColor(R.color.github_organization, null),
                        getString(R.string.organizations)
                    ), ProfileItem(
                        R.drawable.ic_star_24,
                        getColor(R.color.github_starred, null),
                        getString(R.string.starred)
                    )
                )
                }
            }
        }
    }
}