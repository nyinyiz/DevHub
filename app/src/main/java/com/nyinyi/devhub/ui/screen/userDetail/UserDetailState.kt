package com.nyinyi.devhub.ui.screen.userDetail

import com.nyinyi.domain_model.UserDetail

data class UserDetailState(
    val isLoading: Boolean = false,
    val userDetail: UserDetail? = null,
    val throwable: Throwable? = null
)