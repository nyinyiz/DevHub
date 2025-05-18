package com.nyinyi.data.network.response

import com.nyinyi.common.exceptions.Error
import kotlinx.serialization.SerialName

data class ErrorResponse(
    @SerialName("errors")
    val errors: List<ErrorDto>
)

fun ErrorResponse.toModel(): Error {
    return Error(
        message = errors.first().detail
    )
}

data class ErrorDto(
    @SerialName("code")
    val code: String,
    @SerialName("detail")
    val detail: String
)
