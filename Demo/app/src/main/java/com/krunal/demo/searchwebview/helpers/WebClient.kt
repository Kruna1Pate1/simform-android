package com.krunal.demo.searchwebview.helpers

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.krunal.demo.R
import com.krunal.demo.searchwebview.data.repositories.WebViewRepository

class WebClient: WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        val blocked = request?.url?.host in WebViewRepository.getBlockedUrls()
        if (blocked) {
            val unauthorised = view?.resources?.openRawResource(R.raw.unauthorised)?.readBytes()?.decodeToString() ?: ""

            // https://issuetracker.google.com/issues/36915138
            // view?.loadData(unauthorised, "text/html", "charset=UTF-8")

            view?.loadDataWithBaseURL(null, unauthorised, "text/html", "charset=UTF-8", null)
        }
        return blocked
    }
}