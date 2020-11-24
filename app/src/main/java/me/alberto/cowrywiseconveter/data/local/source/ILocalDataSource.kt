package me.alberto.cowrywiseconveter.data.local.source

import androidx.lifecycle.LiveData
import me.alberto.cowrywiseconveter.data.domain.model.Country

interface ILocalDataSource {
    fun getCountries(): LiveData<List<Country>>
    suspend fun addCountry(list: List<Country>)
}