package me.alberto.cowrywiseconveter.data.local.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.alberto.cowrywiseconveter.data.domain.model.Country


@Dao
interface Dao {
    @Query("select * from country_table")
    fun getCountries(): LiveData<List<Country>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCountry(vararg country: Country)
}