package me.alberto.cowrywiseconveter.data.domain.repository

import androidx.lifecycle.LiveData
import me.alberto.cowrywiseconveter.data.domain.model.Country
import me.alberto.cowrywiseconveter.data.remote.source.Result

interface IRepository {
    fun getSymbols(): LiveData<List<Country>>
    suspend fun convert(from: String, to: String, amount: Double): Result<Double>
    suspend fun getHistoryRates(
        startDate: String,
        endDate: String,
        base: String,
        symbol: String
    ): Result<Map<String, Double>>

    suspend fun getSymbolsFromRemote(): Result<Boolean>
}