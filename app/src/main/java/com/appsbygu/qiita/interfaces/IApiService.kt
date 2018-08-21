package com.appsbygu.qiita.interfaces

import com.appsbygu.qiita.models.article.Article
import com.appsbygu.qiita.models.tag.Tag
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

const val AUTH_KEY = "Authorization: Bearer 99aa594b4c5a62850d8e4bb6c484abecabb17d3a"

interface IApiService {

    @Headers(AUTH_KEY)
    @GET("api/v2/items")
    fun articleList(@Query("page") page: Int): io.reactivex.Observable<ArrayList<Article>>

    @Headers(AUTH_KEY)
    @GET("api/v2/tags?sort=count")
    fun tagList(): io.reactivex.Observable<ArrayList<Tag>>
}