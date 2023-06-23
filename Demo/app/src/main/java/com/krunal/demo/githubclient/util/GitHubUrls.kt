package com.krunal.demo.githubclient.util

import com.krunal.demo.utils.AppConstants

object GitHubUrls {

    const val TERM_OF_USE =
        "https://docs.github.com/en/site-policy/github-terms/github-terms-of-service"
    const val PRIVACY_POLICY =
        "https://docs.github.com/en/site-policy/privacy-policies/github-privacy-statement"

    const val BASE_AUTH_URL = "https://github.com/login/oauth/"
    const val AUTHORIZATION_URL =
        BASE_AUTH_URL + "authorize?client_id=" + AppConstants.GITHUB_CLIENT_ID + "&state=" + AppConstants.GITHUB_CLIENT_STATE + "&scope=" + AppConstants.GITHUB_SCOPE

    const val BASE_API_URL = "https://api.github.com/"
}