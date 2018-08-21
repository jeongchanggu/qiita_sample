package com.appsbygu.qiita.models.tag

import com.google.gson.annotations.SerializedName

data class Tag(
        @SerializedName("followers_count") var followersCount: Int?,
        @SerializedName("icon_url") var iconUrl: String?,
        @SerializedName("id") var id: String?,
        @SerializedName("items_count") var itemsCount: Int?
)