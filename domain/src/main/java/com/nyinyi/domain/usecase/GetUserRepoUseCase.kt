package com.nyinyi.domain.usecase

import com.nyinyi.data.repository.GitHubRepository
import javax.inject.Inject

class GetUserRepoUseCase @Inject constructor(
    private val userRepository: GitHubRepository
) {
    operator fun invoke(userName: String) = userRepository.getUserRepositories(userName)
}