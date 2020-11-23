package me.alberto.cowrywiseconveter.data.remote.model

data class HistoryQuery(
    val startDate: String,
    val endDate: String,
    val base: String,
    val symbol: String
)