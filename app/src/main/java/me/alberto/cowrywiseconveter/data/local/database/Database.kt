package me.alberto.cowrywiseconveter.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.alberto.cowrywiseconveter.data.domain.model.Country
import me.alberto.cowrywiseconveter.util.COUNTRY_DATABASE

@Database(entities = [Country::class], version = 1)
abstract class CountryDatabase : RoomDatabase() {
    abstract val dao: Dao
    companion object {
        private lateinit var INSTANCE: CountryDatabase
        fun getCountryDatabase(context: Context): CountryDatabase {
            synchronized(CountryDatabase::class) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE =
                        Room.databaseBuilder(context, CountryDatabase::class.java, COUNTRY_DATABASE)
                            .build()
                }
            }
            return INSTANCE
        }
    }
}