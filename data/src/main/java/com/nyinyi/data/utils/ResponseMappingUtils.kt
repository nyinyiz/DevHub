package com.nyinyi.data.utils

import com.nyinyi.common.exceptions.ApiException
import com.nyinyi.common.exceptions.NoConnectivityException
import com.nyinyi.data.network.response.ErrorResponse
import com.nyinyi.data.network.response.toModel
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.io.InterruptedIOException
import java.net.UnknownHostException
import kotlin.experimental.ExperimentalTypeInference

@OptIn(ExperimentalTypeInference::class)
fun <T> flowTransform(@BuilderInference block: suspend FlowCollector<T>.() -> T) = flow {
    runCatching { block() }
        .onSuccess { result ->
            emit(result)
        }
        .onFailure { exception -> throw exception.mapError() }
}

private fun Throwable.mapError(): Throwable {
    return when (this) {
        is UnknownHostException,
        is InterruptedIOException -> NoConnectivityException

        is HttpException -> {
            val errorResponse = parseErrorResponse(response())
            ApiException(
                errorResponse?.toModel(),
                code(),
                message()
            )
        }

        else -> this
    }
}

private fun parseErrorResponse(response: Response<*>?): ErrorResponse? {
    val jsonString = response?.errorBody()?.string()
    return try {
        jsonString?.let {
            Json.decodeFromString(it)
        }
    } catch (exception: IOException) {
        null
    } catch (exception: SerializationException) {
        null
    } catch (exception: IllegalStateException) {
        null
    }
}