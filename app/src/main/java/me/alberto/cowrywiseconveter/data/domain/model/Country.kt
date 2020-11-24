package me.alberto.cowrywiseconveter.data.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import me.alberto.cowrywiseconveter.util.COUNTRY_TABLE

@Entity(tableName = COUNTRY_TABLE)
data class Country(@PrimaryKey val code: String, val image: String)