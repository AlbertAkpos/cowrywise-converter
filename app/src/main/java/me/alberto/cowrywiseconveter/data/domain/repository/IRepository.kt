package me.alberto.cowrywiseconveter.data.domain.repository

import me.alberto.cowrywiseconveter.data.remote.source.Result

interface IRepository {
    suspend fun getSymbols(): Result<List<String>>
    suspend fun convert(from: String, to: String, amount: Double): Result<Double>
    suspend fun getHistoryRates(
        startDate: String,
        endDate: String,
        base: String,
        symbol: String
    ): Result<Map<String, Double>>
}