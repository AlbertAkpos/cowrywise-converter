package me.alberto.cowrywiseconveter.data.domain.repository

import me.alberto.cowrywiseconveter.data.remote.source.Result

interface IRepository {
    suspend fun getSymbols(): Result<List<String>>
    suspend fun convert(from: String, to: String, amount: Long): Result<Double>
}