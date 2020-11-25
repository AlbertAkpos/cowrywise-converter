package me.alberto.cowrywiseconveter.mockkdata

import me.alberto.cowrywiseconveter.data.domain.model.Country
import me.alberto.cowrywiseconveter.data.remote.source.Result

fun getUSD(): String = "USD"
fun getNGN(): String = "NGN"
fun getAUD(): String = "AUD"
fun getITA(): String = "ITA"
fun getCHN(): String = "CHN"
fun getAmount(): Double = 23.0
fun getNairaValue(): Double = 381.0
fun getCHNValue(): Double = 234.4
fun getITAValue(): Double = 123.4
fun getError():String = "No matching symbol"

fun getSuccessForRemoteCall(): Result<Boolean> {
    return Result.Success(true)
}