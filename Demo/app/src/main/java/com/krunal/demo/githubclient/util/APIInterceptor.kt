package com.krunal.demo.githubclient.util

import android.content.Context
import android.content.Intent
import com.krunal.demo.githubclient.ui.activity.GitHubClientActivity
import com.krunal.demo.helpers.PreferenceHelper
import com.krunal.demo.utils.AppConstants
import com.krunal.demo.utils.PreferenceKeys
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class APIInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val token = if (chain.request().url.encodedPath.endsWith("/token", true)) {
            Credentials.basic(AppConstants.GITHUB_CLIENT_ID, AppConstants.GITHUB_CLIENT_SECRET)
        } else {
            val authToken = PreferenceHelper.getString(PreferenceKeys.AUTHORIZATION_TOKEN, "")
            "Bearer $authToken"
        }

        val request = chain.request().newBuilder().addHeader(
            "Authorization",
            token
        ).build()

        val response = chain.proceed(request)

        // If response is unauthorized and Authorization header already exist, Logout user.
        if (response.code == 401 && response.header("Authorization") != null) {
            logout()
        }

        return response
    }

    private fun logout() {
        val intent = Intent(context, GitHubClientActivity::class.java)
        intent.action = AppConstants.ACTION_GITHUB_LOGOUT
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }
}