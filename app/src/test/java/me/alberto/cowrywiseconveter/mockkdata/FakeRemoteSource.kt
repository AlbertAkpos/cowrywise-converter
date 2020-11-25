package me.alberto.cowrywiseconveter.mockkdata

import me.alberto.cowrywiseconveter.data.remote.source.IRemoteSource
import me.alberto.cowrywiseconveter.data.remote.source.Result

class FakeRemoteSource : IRemoteSource {
    override suspend fun getSymbols(): Result<List<String>> {
        val countries = arrayListOf("USD", "NGN")
        return Result.Success(countries)
    }

    //The Api would essentially be responsible for the conversion. This is just a mock
    //and the values don't represent real exchange rate
    override suspend fun convert(from: String, to: String, amount: Double): Result<Double> {
        if (from == getUSD() && to == getNGN()) {
            return Result.Success(getNairaValue() * amount)
        }

        if (from == getCHN() && to == getNGN()) {
            return Result.Success(getNairaValue() * amount)
        }

        if (from == getUSD() && to == getITA()) {
            return Result.Success(getITAValue() * amount)
        }

        return Result.Failure(getError())
    }

    override suspend fun getRateHistory(
        startDate: String,
        endDate: String,
        base: String,
        symbol: String
    ): Result<Map<String, Double>> {
        TODO("Not yet implemented")
    }
}