package com.appsbygu.qiita.models.article

import com.google.gson.annotations.SerializedName

data class Article(
        @SerializedName("rendered_body") var renderedBody: String?,
        @SerializedName("body") var body: String?,
        @SerializedName("coediting") var coediting: Boolean?,
        @SerializedName("comments_count") var commentsCount: Int?,
        @SerializedName("created_at") var createdAt: String?,
        @SerializedName("group") var group: Any?,
        @SerializedName("id") var id: String?,
        @SerializedName("likes_count") var likesCount: Int?,
        @SerializedName("private") var private: Boolean?,
        @SerializedName("reactions_count") var reactionsCount: Int?,
        @SerializedName("tags") var tags: List<Tag?>?,
        @SerializedName("title") var title: String?,
        @SerializedName("updated_at") var updatedAt: String?,
        @SerializedName("url") var url: String?,
        @SerializedName("user") var user: User?,
        @SerializedName("page_views_count") var pageViewsCount: Any?
)