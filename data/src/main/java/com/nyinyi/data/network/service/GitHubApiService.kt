package com.nyinyi.data.network.service

import com.nyinyi.data.network.response.GitHubUser
import retrofit2.http.GET

interface GitHubApiService {
    @GET("users")
    suspend fun getUsers(): List<GitHubUser>
}