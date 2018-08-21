package com.appsbygu.qiita.models.article

import com.google.gson.annotations.SerializedName

data class Tag(
        @SerializedName("name") var name: String?,
        @SerializedName("versions") var versions: List<Any?>?
)