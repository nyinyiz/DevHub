package com.nyinyi.data.repository

import com.nyinyi.domain_model.Repository
import com.nyinyi.domain_model.User
import com.nyinyi.domain_model.UserDetail
import kotlinx.coroutines.flow.Flow

interface GitHubRepository {
    fun getUsers(): Flow<List<User>>
    fun getUserDetail(username: String): Flow<UserDetail>
    fun getUserRepositories(username: String): Flow<List<Repository>>
    fun getSearchUsers(query: String): Flow<List<User>>
}