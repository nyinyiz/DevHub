package com.nyinyi.data.network.response

data class GitHubSearchUserResponse(
    val items: List<GitHubUser> = emptyList()
)