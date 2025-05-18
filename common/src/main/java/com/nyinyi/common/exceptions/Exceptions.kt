package com.nyinyi.common.exceptions

object NoConnectivityException : RuntimeException("No internet connection")

data class ApiException(
    val error: Error?,
    val httpCode: Int,
    val httpMessage: String?
) : RuntimeException()

data class Error(
    val message: String
)