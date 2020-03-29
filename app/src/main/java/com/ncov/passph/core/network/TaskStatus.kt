package com.ncov.passph.core.network

sealed class TaskStatus<out T : Any> {
    data class Loading<out T : Any>(val loading: Boolean) : TaskStatus<T>()
    data class Success<out T : Any>(val message: String) : TaskStatus<T>()
    data class SuccessWithResult<out T : Any>(val result: T) : TaskStatus<T>()
    data class Failure<out T: Any>(val errorMessage: String) : TaskStatus<T>()
    data class FailureWithException<out T: Any>(val error: Throwable) : TaskStatus<T>()

    companion object {
        fun <T: Any>success(message: String = "") = Success<T>(message)
        fun <T: Any>success(result: T) = SuccessWithResult<T>(result)
        fun <T: Any>failure(errorMessage: String = "Unknown Error") = Failure<T>(errorMessage)
        fun <T: Any>failure(error: Throwable) = FailureWithException<T>(error)
        fun <T: Any>loading(isLoading: Boolean) = Loading<T>(isLoading)
    }
}