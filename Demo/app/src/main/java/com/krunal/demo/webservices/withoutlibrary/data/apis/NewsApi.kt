package com.krunal.demo.webservices.withoutlibrary.data.apis

import com.krunal.demo.utils.AppConstants
import com.krunal.demo.webservices.withoutlibrary.data.models.api.ArticleResponse
import com.krunal.demo.webservices.withoutlibrary.data.models.api.NewsResponse
import com.krunal.demo.webservices.withoutlibrary.data.models.api.SourceResponse
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object NewsApi {

    private const val HEADLINE_URL =
        "${AppConstants.NEWS_BASE_URL}/top-headlines?country=in&apiKey=${AppConstants.NEWS_API_KEY}"

    suspend fun getNews(): NewsResponse? {
        var newsResponse: NewsResponse? = null

        runCatching {
            val url = URL(HEADLINE_URL)
            val urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:221.0) Gecko/20100101 Firefox/31.0")
            val br = BufferedReader(InputStreamReader(urlConnection.inputStream))
            val response = br.readText()

            val newsJson = JSONObject(response)
            val status = newsJson.getString("status")
            val totalResults = newsJson.getInt("totalResults")

            val articles = mutableListOf<ArticleResponse>()

            val articlesJson = newsJson.getJSONArray("articles")

            for (i in 0 until articlesJson.length()) {
                if (i == 19) {
                    print("")
                }
                val articleJson = articlesJson.getJSONObject(i)
                val sourceJson = articleJson.getJSONObject("source")
                val source =
                    SourceResponse(sourceJson.optString("id"), sourceJson.optString("name"))
                val article = ArticleResponse(
                    source = source,
                    author = articleJson.optString("author"),
                    title = articleJson.getString("title"),
                    description = articleJson.getString("description"),
                    url = articleJson.getString("url"),
                    urlToImage = articleJson.getString("urlToImage"),
                    publishedAt = articleJson.getString("publishedAt"),
                    content = articleJson.getString("content"),
                )
                articles.add(article)
            }

            newsResponse = NewsResponse(
                status, totalResults, articles
            )
        }
        return newsResponse
    }
}