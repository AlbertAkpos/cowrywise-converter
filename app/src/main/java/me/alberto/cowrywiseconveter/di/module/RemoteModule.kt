package me.alberto.cowrywiseconveter.di.module

import dagger.Module
import dagger.Provides
import me.alberto.cowrywiseconveter.data.remote.api.RestClient

@Module
class RemoteModule {
    @Provides
    fun provideRestClient(): RestClient = RestClient()
}