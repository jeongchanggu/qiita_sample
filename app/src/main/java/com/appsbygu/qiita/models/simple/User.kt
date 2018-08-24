package com.appsbygu.qiita.models.simple

import com.google.gson.annotations.SerializedName

data class User(
        @SerializedName("id") var id: String?,
        @SerializedName("name") var name: String?,
        @SerializedName("profile_image_url") var profileImageUrl: String?
)