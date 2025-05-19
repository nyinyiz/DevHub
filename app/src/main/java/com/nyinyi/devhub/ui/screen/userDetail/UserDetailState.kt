package com.nyinyi.devhub.ui.screen.userDetail

import com.nyinyi.domain_model.Repository
import com.nyinyi.domain_model.UserDetail

data class UserDetailState(
    val isLoading: Boolean = false,
    val userDetail: UserDetail? = null,
    val isRepoLoading: Boolean = false,
    val userRepos: List<Repository> = emptyList(),
    val throwable: Throwable? = null
)
