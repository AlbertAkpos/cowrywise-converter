package me.alberto.cowrywiseconveter.data.remote.source

sealed class Result<T> {
    data class Success<T>(val data: T): Result<T>()
    data class Failure<T>(val error: String):Result<T>()
}