package com.krunal.demo.webservices.withoutlibrary.repositories

import com.krunal.demo.webservices.withoutlibrary.data.apis.NewsApi
import com.krunal.demo.webservices.withoutlibrary.data.models.api.ArticleResponse

object NewsRepository {

    suspend fun getNewsArticles(): List<ArticleResponse> =
        NewsApi.getNews()?.articles ?: emptyList()
}