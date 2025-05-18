package com.nyinyi.data.repository

import com.nyinyi.domain_model.User
import kotlinx.coroutines.flow.Flow

interface GitHubRepository {
    fun getUsers(): Flow<List<User>>
}