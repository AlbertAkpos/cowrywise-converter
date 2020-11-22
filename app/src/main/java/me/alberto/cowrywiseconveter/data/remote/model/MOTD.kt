package me.alberto.cowrywiseconveter.data.remote.model

import com.google.gson.annotations.SerializedName

data class MOTD(
    @SerializedName("msg")
    val msg: String,
    @SerializedName("url")
    val url: String
)