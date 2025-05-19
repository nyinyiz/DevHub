package com.nyinyi.data.network.response

import com.google.gson.annotations.SerializedName

data class GitHubUserRepoResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("private") val private: Boolean,
    @SerializedName("description") val description: String?,
    @SerializedName("fork") val fork: Boolean,
    @SerializedName("language") val language: String?,
    @SerializedName("stargazers_count") val starCount: Int,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("updated_at") val updatedAt: String
)