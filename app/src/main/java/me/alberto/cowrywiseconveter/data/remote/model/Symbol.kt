package me.alberto.cowrywiseconveter.data.remote.model

import com.google.gson.annotations.SerializedName

data class Symbol(
    @SerializedName("description")
    val description: String,
    @SerializedName("code")
    val code: String
)