package me.alberto.cowrywiseconveter.data.remote.response

import com.google.gson.annotations.SerializedName
import me.alberto.cowrywiseconveter.data.remote.model.Info
import me.alberto.cowrywiseconveter.data.remote.model.MOTD
import me.alberto.cowrywiseconveter.data.remote.model.Query

data class ConvertResponse(
    @SerializedName("motd")
    val motd: MOTD,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("query")
    val query: Query,
    @SerializedName("info")
    val info: Info,
    @SerializedName("historical")
    val historical: Boolean,
    @SerializedName("date")
    val date: String,
    @SerializedName("result")
    val result: Double
 )