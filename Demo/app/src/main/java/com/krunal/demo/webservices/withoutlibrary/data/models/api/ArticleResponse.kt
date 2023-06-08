package com.krunal.demo.webservices.withoutlibrary.data.models.api

data class ArticleResponse(
    val source: SourceResponse,
    val author: String? = null,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)
