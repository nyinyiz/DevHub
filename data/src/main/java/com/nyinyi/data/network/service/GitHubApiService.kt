package com.nyinyi.data.network.service

import com.nyinyi.data.network.response.GitHubUsersResponse
import retrofit2.http.GET

interface GitHubApiService {
    @GET("users")
    fun getUsers(): GitHubUsersResponse
}