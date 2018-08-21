package com.appsbygu.qiita.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.appsbygu.qiita.R
import kotlinx.android.synthetic.main.activity_article.*

class ArticleActivity : AppCompatActivity() {

    companion object {
        private const val HTML_CODE = "htmlCode"

        fun createIntent(context: Context, html: String): Intent {
            return Intent(context, ArticleActivity::class.java)
                    .putExtra(HTML_CODE, html)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        val htmlCode = intent.getStringExtra(HTML_CODE)

        articleWebView.loadData(htmlCode, "text/html", null)
        articleWebView.settings.useWideViewPort = true
        articleWebView.settings.loadWithOverviewMode = true
        articleWebView.settings.builtInZoomControls = true
    }
}