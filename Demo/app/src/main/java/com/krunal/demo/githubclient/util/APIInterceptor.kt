package com.krunal.demo.githubclient.util

import android.util.Base64
import com.krunal.demo.helpers.PreferenceHelper
import com.krunal.demo.utils.AppConstants
import com.krunal.demo.utils.PreferenceKeys
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class APIInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val token = if (chain.request().url.encodedPath.endsWith("/token", true) ){
            Credentials.basic(AppConstants.GITHUB_CLIENT_ID, AppConstants.GITHUB_CLIENT_SECRET)
        } else {
            "Bearer "  + PreferenceHelper.getString(PreferenceKeys.AUTHORIZATION_TOKEN, "")
        }

        val request = chain.request().newBuilder().addHeader(
            "Authorization",
            token
        ).build()
        return chain.proceed(request)
    }
}