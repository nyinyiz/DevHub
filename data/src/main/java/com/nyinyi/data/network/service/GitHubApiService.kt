package com.nyinyi.data.network.service

import com.nyinyi.data.network.response.GitHubUser
import com.nyinyi.data.network.response.GitHubUserDetailResponse
import com.nyinyi.data.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApiService {
    @GET(Constants.Network.GET_USER_LIST)
    suspend fun getUsers(
        @Query("per_page") perPage: Int = Constants.Network.DEFAULT_PAGE,
    ): List<GitHubUser>

    @GET(Constants.Network.GET_USER_DETAIL)
    suspend fun getUserDetail(@Path("username") username: String): GitHubUserDetailResponse
}