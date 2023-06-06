package com.krunal.demo.searchwebview.utils

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.webkit.WebViewDatabase
import com.krunal.demo.R
import com.krunal.demo.searchwebview.data.repositories.WebViewRepository

class WebClient: WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        val blocked = request?.url?.host in WebViewRepository.getBlockedUrls()
        if (blocked) {
            val unauthorised = view?.resources?.openRawResource(R.raw.unauthorised)?.readBytes()?.decodeToString() ?: ""
            view?.loadData(unauthorised, "text/html", "charset=UTF-8")
        }
        return blocked
    }
}