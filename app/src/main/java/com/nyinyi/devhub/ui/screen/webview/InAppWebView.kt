package com.nyinyi.devhub.ui.screen.webview

import android.annotation.SuppressLint
import android.os.Build
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun InAppWebView(url: String, modifier: Modifier = Modifier) {
    var webView: WebView? = null

    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { context ->

            WebView(context).apply {
                webView = this

                settings.javaScriptEnabled = true

                settings.cacheMode = WebSettings.LOAD_DEFAULT
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    WebView.setWebContentsDebuggingEnabled(true)
                }

                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
                        view?.loadUrl(url)
                        return true
                    }
                }

                loadUrl(url)
                this
            }
        },
        update = { view ->
            webView = view
        }
    )

    DisposableEffect(key1 = url) {
        onDispose {
            webView?.destroy()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InAppWebViewPreview() {
    InAppWebView(url = "https:google.com")
}
