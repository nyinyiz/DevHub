package com.nyinyi.domain_model

import androidx.compose.runtime.Immutable

@Immutable
data class User(
    val id: Int,
    val name: String,
    val avatar: String
)