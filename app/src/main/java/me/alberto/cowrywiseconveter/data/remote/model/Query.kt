package me.alberto.cowrywiseconveter.data.remote.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Query (
    val from: String,
    val to: String,
    val amount: Double
): Parcelable