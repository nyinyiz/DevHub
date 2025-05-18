package com.nyinyi.data.mapper

import com.nyinyi.data.network.response.GitHubUsersResponse
import com.nyinyi.domain_model.User
import javax.inject.Inject

class GitHubUserMapper @Inject constructor() {

    fun mapToUserDomain(userResponse: GitHubUsersResponse): List<User> {
        return userResponse.items.map {
            User(
                id = it.id,
                name = it.login,
                avatar = it.avatarUrl,
            )
        }.orEmpty()
    }
}