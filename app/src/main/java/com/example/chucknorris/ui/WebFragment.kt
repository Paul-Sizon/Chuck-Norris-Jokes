package com.example.chucknorris.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.chucknorris.R


class WebFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_web, container, false)

        val webView = view.findViewById<WebView>(R.id.webview)
        val webSetting: WebSettings = webView.settings
        webSetting.builtInZoomControls = true
        webSetting.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        val URL = "http://www.icndb.com/api"


        webView.loadUrl(URL)

        return view
    }



}