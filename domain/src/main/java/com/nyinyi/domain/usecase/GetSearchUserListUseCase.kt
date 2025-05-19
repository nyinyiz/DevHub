package com.nyinyi.domain.usecase

import com.nyinyi.data.repository.GitHubRepository
import javax.inject.Inject

class GetSearchUserListUseCase @Inject constructor(
    private val userRepository: GitHubRepository
) {
    operator fun invoke(query: String) = userRepository.getSearchUsers(query)
}