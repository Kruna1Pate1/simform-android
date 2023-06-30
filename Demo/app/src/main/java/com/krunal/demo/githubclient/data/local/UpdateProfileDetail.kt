package com.krunal.demo.githubclient.data.local

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.krunal.demo.BR
import com.krunal.demo.githubclient.data.remote.model.response.UserResponse

data class UpdateProfileDetail(
    private var _avatar: String?,
    private var _name: String?,
    private var _email: String,
    private var _bio: String?,
    private var _website: String?,
    private var _twitter: String?,
    private var _company: String?
) : BaseObservable() {

    @get:Bindable
    var avatar: String? = _avatar
        set(value) {
            _avatar = value
            field = value
            notifyPropertyChanged(BR.avatar)
        }

    @get:Bindable
    var name: String? = _name
        set(value) {
            _name = value
            field = value
            notifyPropertyChanged(BR.name)
        }

    @get:Bindable
    var email: String = _email
        set(value) {
            _email = value
            field = value
            notifyPropertyChanged(BR.email)
        }

    @get:Bindable
    var bio: String? = _bio
        set(value) {
            _bio = value
            field = value
            notifyPropertyChanged(BR.bio)
        }

    @get:Bindable
    var website: String? = _website
        set(value) {
            _website = value
            field = value
            notifyPropertyChanged(BR.website)
        }

    @get:Bindable
    var twitter: String? = _twitter
        set(value) {
            _twitter = value
            field = value
            notifyPropertyChanged(BR.twitter)
        }

    @get:Bindable
    var company: String? = _company
        set(value) {
            _company = value
            field = value
            notifyPropertyChanged(BR.company)
        }

    companion object {

        fun from(userResponse: UserResponse): UpdateProfileDetail = with(userResponse) {
            UpdateProfileDetail(
                avatarUrl, name, email, bio, blog, twitterUsername, company
            )
        }
    }
}