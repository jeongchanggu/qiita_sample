package com.appsbygu.qiita.fragments

import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast
import com.appsbygu.qiita.R
import com.appsbygu.qiita.adapters.ArticleAdapter
import com.appsbygu.qiita.models.article.Article
import com.appsbygu.qiita.services.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

abstract class ContentFragment : Fragment() {
    protected var articles: ArrayList<Article> = ArrayList()
    private val disposables = CompositeDisposable()
    private var page: Int = 0
    private var progressCallback: (Int) -> Unit = {}
    private var onclickCallback: (String) -> Unit = {}
    private var progressStatus = false

    lateinit var recyclerView: RecyclerView

    override fun onStart() {
        if (page == 0) fetchArticle()

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!recyclerView.canScrollVertically(1)) fetchArticle()
            }
        })
        super.onStart()
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }

    fun setProgressCallback(callback: ((Int) -> Unit)) {
        progressCallback = callback
    }

    fun setOnclickCallback(callback: ((String) -> Unit)) {
        onclickCallback = callback
    }

    private fun fetchArticle() {
        if (progressStatus) return
        progressStatus = true

        if(page > 0) progressCallback.invoke(View.VISIBLE)
        var dispose = ApiService.instance.getService()
                .articleList(++page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::afterArticleLoaded, this::afterArticleLoadedFail)
        disposables.add(dispose)
    }

    private fun afterArticleLoaded(articles: ArrayList<Article>) {
        addArticles(articles)
        progressStatus = false
        if(page > 1) {
            progressCallback.invoke(View.INVISIBLE)
            Toast.makeText(this.context, "PAGE : $page loaded", Toast.LENGTH_SHORT).show()
        }
    }

    private fun afterArticleLoadedFail(error: Throwable) {
        Toast.makeText(this.context, getString(R.string.error_text_load_api), Toast.LENGTH_LONG).show()
    }

    private fun addArticles(articles: ArrayList<Article>) {
        var adapter = recyclerView.adapter as ArticleAdapter
        adapter.addArticles(articles)
        adapter.notifyDataSetChanged()
        adapter.setOnclickCallback { onclickCallback(it) }
    }
}