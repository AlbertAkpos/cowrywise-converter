package me.alberto.cowrywiseconveter.data.remote.source

interface IRemoteSource {
    suspend fun getSymbols(): Result<List<String>>
    suspend fun convert(from: String, to: String, amount: Double): Result<Double>
    suspend fun getRateHistory(startDate: String, endDate: String, base: String, symbol: String): Result<Map<String, Double>>
}