package com.appsbygu.qiita.fragments

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appsbygu.qiita.R
import com.appsbygu.qiita.adapters.ArticleAdapter


class TileContentFragment: ContentFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        recyclerView = inflater.inflate(R.layout.recycler_view, container, false) as RecyclerView
        recyclerView.adapter = ArticleAdapter(articles, R.layout.item_tile)
        recyclerView.setHasFixedSize(false)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)

        return recyclerView
    }

}