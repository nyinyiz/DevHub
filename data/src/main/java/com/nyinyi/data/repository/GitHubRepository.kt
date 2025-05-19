package com.nyinyi.data.repository

import com.nyinyi.domain_model.User
import com.nyinyi.domain_model.UserDetail
import kotlinx.coroutines.flow.Flow

interface GitHubRepository {
    fun getUsers(): Flow<List<User>>
    fun getUserDetail(username: String): Flow<UserDetail>
}