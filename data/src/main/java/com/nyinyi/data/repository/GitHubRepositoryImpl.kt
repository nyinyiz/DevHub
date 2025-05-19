package com.nyinyi.data.repository

import com.nyinyi.data.mapper.GitHubUserMapper
import com.nyinyi.data.network.response.GitHubUsersResponse
import com.nyinyi.data.network.service.GitHubApiService
import com.nyinyi.data.utils.flowTransform
import javax.inject.Inject

class GitHubRepositoryImpl @Inject constructor(
    private val api: GitHubApiService,
    private val mapper: GitHubUserMapper
) : GitHubRepository {
    override fun getUsers() = flowTransform {
        api.getUsers().let {
            mapper.mapToUserDomain(GitHubUsersResponse(it))
        }
    }

    override fun getUserDetail(username: String) = flowTransform {
        api.getUserDetail(username).let {
            mapper.mapToUserDetailDomain(it)
        }
    }
}