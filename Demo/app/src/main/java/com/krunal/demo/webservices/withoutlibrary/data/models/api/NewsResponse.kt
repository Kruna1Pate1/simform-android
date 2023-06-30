package com.krunal.demo.webservices.withoutlibrary.data.models.api

data class NewsResponse(
    val status: String,
    val totalResult: Int,
    val articles: List<ArticleResponse>
)
