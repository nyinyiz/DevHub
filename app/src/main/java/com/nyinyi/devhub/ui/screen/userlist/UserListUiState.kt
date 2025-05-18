package com.nyinyi.devhub.ui.screen.userlist

import com.nyinyi.domain_model.User

data class UserListUiState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val throwable: Throwable? = null
)