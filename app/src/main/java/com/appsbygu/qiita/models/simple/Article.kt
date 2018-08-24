package com.appsbygu.qiita.models.simple

import com.google.gson.annotations.SerializedName

data class Article(
        @SerializedName("id") var id: String?,
        @SerializedName("likes_count") var likesCount: Int?,
        @SerializedName("title") var title: String?,
        @SerializedName("user") var user: User?
)