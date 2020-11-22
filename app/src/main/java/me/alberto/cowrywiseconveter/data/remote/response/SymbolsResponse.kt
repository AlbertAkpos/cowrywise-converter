package me.alberto.cowrywiseconveter.data.remote.response

import com.google.gson.annotations.SerializedName
import me.alberto.cowrywiseconveter.data.remote.model.MOTD
import me.alberto.cowrywiseconveter.data.remote.model.Symbol

data class SymbolsResponse(
    @SerializedName("motd")
    val motd: MOTD,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("symbols")
    val symbols: Map<String, Symbol>
)