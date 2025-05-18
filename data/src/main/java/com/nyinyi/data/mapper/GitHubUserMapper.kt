package com.nyinyi.data.mapper

import com.nyinyi.data.network.response.GitHubUsersResponse
import com.nyinyi.domain_model.User

class GitHubUserMapper {

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