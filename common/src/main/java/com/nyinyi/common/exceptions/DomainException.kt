package com.nyinyi.common.exceptions

open class DomainException(override val message: String?) : Throwable(message)

data class InvalidDataException(
    val paramName: String
) : DomainException("$paramName is not valid.")

object NoConnectionException : DomainException("No connection available.")

object DisconnectException : DomainException("Network disconnected.")