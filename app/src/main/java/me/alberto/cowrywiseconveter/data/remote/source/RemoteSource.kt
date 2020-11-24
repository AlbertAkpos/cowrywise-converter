package me.alberto.cowrywiseconveter.data.remote.source

import me.alberto.cowrywiseconveter.data.remote.api.RestClient
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.collections.LinkedHashMap

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
            val array = ArrayList<Double>()
            val keys = ArrayList<String>()
            response.rates.keys.map { keys.add(it) }
            response.rates.forEach { returnedRates ->
                returnedRates.value.forEach {
                    array.add(it.value)
                }
            }

            val rates = LinkedHashMap<String, Double>()

            for (i in array.indices) {
                val key = keys[i]
                val value = array[i]
                rates[key] = value
            }

            Result.Success(rates)
        } else {
            Result.Failure("Some error occurred. Please try again")
        }
    }
}