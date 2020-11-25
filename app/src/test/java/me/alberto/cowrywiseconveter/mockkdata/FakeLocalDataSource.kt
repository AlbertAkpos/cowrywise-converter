package me.alberto.cowrywiseconveter.mockkdata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.alberto.cowrywiseconveter.data.domain.model.Country
import me.alberto.cowrywiseconveter.data.local.source.ILocalDataSource

class FakeLocalDataSource : ILocalDataSource {
    override fun getCountries(): LiveData<List<Country>> {
        val liveList = MutableLiveData<List<Country>>()
        liveList.value = database
        return liveList
    }

    override suspend fun addCountry(list: List<Country>) {
        database.addAll(list)
    }
}