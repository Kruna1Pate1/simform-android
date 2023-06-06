package com.krunal.demo.searchwebview.data.repositories

object WebViewRepository {

    fun getBlockedUrls(): List<String> = listOf(
        "facebook.com",
        "youtube.com"
    )
}