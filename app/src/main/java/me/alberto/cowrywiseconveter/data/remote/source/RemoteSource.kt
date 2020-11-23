package me.alberto.cowrywiseconveter.data.remote.source

import me.alberto.cowrywiseconveter.data.remote.api.RestClient
import javax.inject.Inject

class RemoteSource @Inject constructor(private val restClient: RestClient) : IRemoteSource {
    override suspend fun getSymbols(): Result<List<String>> {
        val response = restClient.getRemote().getSymbols()
        return if (response.success) {
            val symbols = response.symbols.map { it.key }
            Result.Success(symbols)
        } else {
            Result.Failure("Some error occurred. Please try again")
        }
    }

    override suspend fun convert(from: String, to: String, amount: Double): Result<Double> {
        val response = restClient.getRemote().convert(from, to, amount)
        return if (response.success) {
            Result.Success(response.result)
        } else {
            Result.Failure("Some error occurred. Please try again")
        }
    }

    override suspend fun getRateHistory(
        startDate: String,
        endDate: String,
        base: String,
        symbol: String
    ): Result<Map<String, Double>> {
        val response = restClient.getRemote().getRateHistory(startDate, endDate, base, symbol)
        return if (response.success) {
            val rates = HashMap<String, Double>()
            response.rates.forEach { returnedRates ->
                returnedRates.value.forEach {
                    rates[returnedRates.key] = it.value
                }
            }
            Result.Success(rates)
        } else {
            Result.Failure("Some error occurred. Please try again")
        }
    }
}