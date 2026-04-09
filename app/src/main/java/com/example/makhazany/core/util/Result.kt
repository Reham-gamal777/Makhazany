package com.example.makhazany.core.util

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Failure(val error: AppError) : Result<Nothing>()
}

sealed class AppError {
    data class NetworkError(val message: String) : AppError()
    data class ParsingError(val message: String) : AppError()
    data class UnknownError(val message: String) : AppError()
}
