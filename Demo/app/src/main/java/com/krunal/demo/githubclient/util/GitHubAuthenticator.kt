package com.krunal.demo.githubclient.util

import com.krunal.demo.githubclient.di.ApiModule
import com.krunal.demo.helpers.PreferenceHelper
import com.krunal.demo.utils.PreferenceKeys
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class GitHubAuthenticator(
    preferenceHelper: PreferenceHelper
) : Authenticator {

    private val token = preferenceHelper.getString(PreferenceKeys.AUTHORIZATION_TOKEN, "")

    override fun authenticate(route: Route?, response: Response): Request? {
        if (token.isEmpty()) {
            return null
        }

        if (response.request.header(AUTHORIZATION) != null) {
            // Give up, Already attempted to authenticate.
            return null
        }

        return response.request.newBuilder().addHeader(AUTHORIZATION, "Bearer $token").build()
    }

    companion object {
        private const val AUTHORIZATION = "Authorization"
    }
}