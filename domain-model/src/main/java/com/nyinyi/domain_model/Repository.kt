package com.nyinyi.domain_model

data class Repository(
    val id: Long,
    val name: String,
    val fullName: String,
    val private: Boolean,
    val description: String,
    val fork: Boolean,
    val language: String,
    val starCount: Int,
    val htmlUrl: String,
)