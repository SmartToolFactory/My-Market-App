package com.smarttoolfactory.mymarket.data.model

/**
 * Wrapper class for error and success states for retrieving data
 */
sealed class Result<out T> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error<out T>(val error: Throwable) : Result<T>()

}