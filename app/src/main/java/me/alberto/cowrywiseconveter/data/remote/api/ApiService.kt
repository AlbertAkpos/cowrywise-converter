package me.alberto.cowrywiseconveter.data.remote.api

import me.alberto.cowrywiseconveter.data.remote.response.ConvertResponse
import me.alberto.cowrywiseconveter.data.remote.response.HistoryResponse
import me.alberto.cowrywiseconveter.data.remote.response.SymbolsResponse
import me.alberto.cowrywiseconveter.util.Urls
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(Urls.SYMBOLS)
    suspend fun getSymbols(): SymbolsResponse

    @GET(Urls.CONVERT)
    suspend fun convert(
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: Double
    ): ConvertResponse

    @GET(Urls.RATE_HISTORY)
    suspend fun getRateHistory(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("base") base: String,
        @Query("symbols") symbols: String
    ): HistoryResponse
}