package com.nyinyi.data.mapper

import com.nyinyi.data.network.response.GitHubUserDetailResponse
import com.nyinyi.data.network.response.GitHubUserRepoResponse
import com.nyinyi.data.network.response.GitHubUsersResponse
import com.nyinyi.domain_model.Repository
import com.nyinyi.domain_model.User
import com.nyinyi.domain_model.UserDetail
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

    fun mapToUserDetailDomain(userResponse: GitHubUserDetailResponse): UserDetail {
        return UserDetail(
            login = userResponse.login ?: "",
            id = userResponse.id ?: 0,
            nodeId = userResponse.nodeId ?: "",
            avatarUrl = userResponse.avatarUrl ?: "",
            gravatarId = userResponse.gravatarId ?: "",
            url = userResponse.url ?: "",
            htmlUrl = userResponse.htmlUrl ?: "",
            followersUrl = userResponse.followersUrl ?: "",
            followingUrl = userResponse.followingUrl ?: "",
            gistsUrl = userResponse.gistsUrl ?: "",
            starredUrl = userResponse.starredUrl ?: "",
            subscriptionsUrl = userResponse.subscriptionsUrl ?: "",
            organizationsUrl = userResponse.organizationsUrl ?: "",
            reposUrl = userResponse.reposUrl ?: "",
            eventsUrl = userResponse.eventsUrl ?: "",
            receivedEventsUrl = userResponse.receivedEventsUrl ?: "",
            type = userResponse.type ?: "",
            userViewType = userResponse.userViewType ?: "",
            siteAdmin = userResponse.siteAdmin == true,
            name = userResponse.name ?: "",
            company = userResponse.company ?: "",
            blog = userResponse.blog ?: "",
            location = userResponse.location ?: "",
            email = userResponse.email ?: "",
            hireable = userResponse.hireable == true,
            bio = userResponse.bio ?: "",
            twitterUsername = userResponse.twitterUsername ?: "",
            publicRepos = userResponse.publicRepos ?: 0,
            publicGists = userResponse.publicGists ?: 0,
            followers = userResponse.followers ?: 0,
            following = userResponse.following ?: 0,
            createdAt = userResponse.createdAt ?: "",
            updatedAt = userResponse.updatedAt ?: "",
        )
    }

    fun mapToRepositoryDomain(userResponse: List<GitHubUserRepoResponse>): List<Repository> {
        return userResponse.map {
            Repository(
                id = it.id ?: 0,
                name = it.name ?: "",
                fullName = it.fullName ?: "",
                description = it.description ?: "",
                language = it.language ?: "",
                private = it.private ?: false,
                htmlUrl = it.htmlUrl ?: "",
                fork = it.fork ?: false,
                starCount = it.starCount ?: 0,
            )
        }.orEmpty()
    }
}