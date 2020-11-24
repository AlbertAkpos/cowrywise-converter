package me.alberto.cowrywiseconveter.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import me.alberto.cowrywiseconveter.data.local.database.CountryDatabase
import javax.inject.Singleton

@Module
class LocalModule {

    @Provides
    fun provideDatabase(context: Context): CountryDatabase {
        return CountryDatabase.getCountryDatabase(context)
    }

}