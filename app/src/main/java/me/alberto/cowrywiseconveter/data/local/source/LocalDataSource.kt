package me.alberto.cowrywiseconveter.data.local.source

import androidx.lifecycle.LiveData
import me.alberto.cowrywiseconveter.data.domain.model.Country
import me.alberto.cowrywiseconveter.data.local.database.CountryDatabase
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val database: CountryDatabase
) :
    ILocalDataSource {

    override fun getCountries(): LiveData<List<Country>> = database.dao.getCountries()

    override suspend fun addCountry(list: List<Country>) {
        database.dao.addCountry(*list.toTypedArray())
    }
}