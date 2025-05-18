package com.nyinyi.data.network.service

import com.nyinyi.data.network.response.GitHubUser
import com.nyinyi.data.network.response.GitHubUsersResponse
import retrofit2.http.GET

interface GitHubApiService {
    @GET("users")
    suspend fun getUsers(): List<GitHubUser>
}