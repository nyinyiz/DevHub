package com.nyinyi.domain.usecase

import com.nyinyi.data.repository.GitHubRepository
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val userRepository: GitHubRepository
) {
    operator fun invoke() = userRepository.getUsers()
}