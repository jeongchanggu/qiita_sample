package com.appsbygu.qiita.fragments

import android.content.Context
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
    private var progressStatus = false
    lateinit var recyclerView: RecyclerView

    var activityCallback: ContentFragment.ContentListener? = null

    interface ContentListener {
        fun articleClick (html: String)
        fun progressStatus (visibility: Int)
    }

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

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        activityCallback = context as ContentListener
    }

    private fun fetchArticle() {
        if (progressStatus) return
        progressStatus = true

        if(page > 0) activityCallback?.progressStatus(View.VISIBLE)
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
            activityCallback?.progressStatus(View.INVISIBLE)
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
        adapter.setOnclickCallback { activityCallback?.articleClick(it) }
    }
}