package me.alberto.cowrywiseconveter.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import me.alberto.cowrywiseconveter.data.local.database.CountryDatabase
import me.alberto.cowrywiseconveter.di.scopes.AppScope
import javax.inject.Singleton

@Module
class LocalModule {

    @Provides
    @AppScope
    fun provideDatabase(context: Context): CountryDatabase {
        return CountryDatabase.getCountryDatabase(context)
    }

}