package com.appsbygu.qiita.models.article

import com.google.gson.annotations.SerializedName

data class User(
        @SerializedName("description") var description: String?,
        @SerializedName("facebook_id") var facebookId: String?,
        @SerializedName("followees_count") var followeesCount: Int?,
        @SerializedName("followers_count") var followersCount: Int?,
        @SerializedName("github_login_name") var githubLoginName: String?,
        @SerializedName("id") var id: String?,
        @SerializedName("items_count") var itemsCount: Int?,
        @SerializedName("linkedin_id") var linkedinId: String?,
        @SerializedName("location") var location: String?,
        @SerializedName("name") var name: String?,
        @SerializedName("organization") var organization: String?,
        @SerializedName("permanent_id") var permanentId: Int?,
        @SerializedName("profile_image_url") var profileImageUrl: String?,
        @SerializedName("twitter_screen_name") var twitterScreenName: String?,
        @SerializedName("website_url") var websiteUrl: String?
)