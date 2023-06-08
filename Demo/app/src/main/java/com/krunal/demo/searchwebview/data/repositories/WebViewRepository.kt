package com.krunal.demo.searchwebview.data.repositories

object WebViewRepository {

    private val urls = listOf(
        "https://krunalpatel.me",
        "https://blog.krunalpatel.me",
        "https://instagram.com",
        "https://facebook.com",
        "https://m.facebook.com",
        "https://youtube.com",
        "https://shodan.io"
    )

    fun getBlockedUrls(): List<String> = listOf(
        "facebook.com",
        "m.facebook.com",
        "youtube.com",
        "m.youtube.com"
    )

    fun getRandomUrl(): String = "https://google.com"//urls.random()
}