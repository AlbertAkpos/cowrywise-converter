package me.alberto.cowrywiseconveter.data.repository

import androidx.lifecycle.LiveData
import me.alberto.cowrywiseconveter.data.domain.model.Country
import me.alberto.cowrywiseconveter.data.domain.repository.IRepository
import me.alberto.cowrywiseconveter.data.local.source.ILocalDataSource
import me.alberto.cowrywiseconveter.data.remote.source.RemoteSource
import me.alberto.cowrywiseconveter.data.remote.source.Result
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteSource: RemoteSource,
    private val localSource: ILocalDataSource
) : IRepository {
    override fun getSymbols(): LiveData<List<Country>> {
        return localSource.getCountries()
    }

    override suspend fun convert(from: String, to: String, amount: Double): Result<Double> {
        return remoteSource.convert(from, to, amount)
    }

    override suspend fun getHistoryRates(
        startDate: String,
        endDate: String,
        base: String,
        symbol: String
    ): Result<Map<String, Double>> {
        return remoteSource.getRateHistory(startDate, endDate, base, symbol)
    }

    override suspend fun getSymbolsFromRemote(): Result<Boolean> {
        val result = remoteSource.getSymbols()
        if (result is Result.Success) {
            val list = result.data.map { Country(it, it) }
            try {
                localSource.addCountry(list)
            } catch (exp: Exception) {
                exp.printStackTrace()
            }
            return Result.Success(true)
        }
        result as Result.Failure
        return Result.Failure(result.error)
    }
}