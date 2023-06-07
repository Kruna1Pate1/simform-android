package com.krunal.demo.searchwebview.helpers

import android.content.Intent
import android.net.Uri
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.net.toUri
import com.krunal.demo.R
import com.krunal.demo.searchwebview.data.repositories.WebViewRepository

class WebClient : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        if (view == null || request == null) return true

        if ("${request.url.scheme}:" in listOf(WebView.SCHEME_MAILTO, WebView.SCHEME_TEL)) {
            Intent(Intent.ACTION_VIEW, request.url.normalizeScheme()).also { intent ->
                view.context.startActivity(intent)
            }
            return true
        }

        val blocked = request.url?.host in WebViewRepository.getBlockedUrls()
        if (blocked) {
            val unauthorised =
                view.resources.openRawResource(R.raw.unauthorised).readBytes()?.decodeToString()
                    ?: ""

            // https://issuetracker.google.com/issues/36915138
            // view?.loadData(unauthorised, "text/html", "charset=UTF-8")

            view.loadDataWithBaseURL(null, unauthorised, "text/html", "charset=UTF-8", null)
        }
        return blocked
    }
}