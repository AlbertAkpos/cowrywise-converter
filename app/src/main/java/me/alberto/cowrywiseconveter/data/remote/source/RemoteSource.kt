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
           Result.Success( response.result)
        } else {
            Result.Failure("Some error occurred. Please try again")
        }
    }
}