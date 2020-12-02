package com.example.chucknorris.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.chucknorris.databinding.FragmentWebBinding


class WebFragment : Fragment() {

    private lateinit var webView: WebView
    private lateinit var fragmentBinding: FragmentWebBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = FragmentWebBinding.inflate(inflater, container, false)

        webView = fragmentBinding.webview
        webView.webViewClient = WebViewClient()
        val url = "http://www.icndb.com/api"
        webView.loadUrl(url)

        return fragmentBinding.root
    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        webView.saveState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            webView.restoreState(savedInstanceState)
        }
    }
}