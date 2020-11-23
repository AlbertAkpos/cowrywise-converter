package me.alberto.cowrywiseconveter.data.remote.response

import com.google.gson.annotations.SerializedName
import me.alberto.cowrywiseconveter.data.remote.model.MOTD

data class HistoryResponse(
    @SerializedName("motd")
    val motd: MOTD,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("timeseries")
    val timeseries: Boolean,
    @SerializedName("base")
    val base: String,
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("end_date")
    val endDate: String,
    @SerializedName("rates")
    val rates: Map<String, Map<String, Double>>
)