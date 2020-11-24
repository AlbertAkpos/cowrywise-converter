package me.alberto.cowrywiseconveter.di.module

import dagger.Binds
import dagger.Module
import me.alberto.cowrywiseconveter.data.domain.repository.IRepository
import me.alberto.cowrywiseconveter.data.local.source.ILocalDataSource
import me.alberto.cowrywiseconveter.data.local.source.LocalDataSource
import me.alberto.cowrywiseconveter.data.remote.source.IRemoteSource
import me.alberto.cowrywiseconveter.data.remote.source.RemoteSource
import me.alberto.cowrywiseconveter.data.repository.Repository

@Module
abstract class DataModule {
    @Binds
    abstract fun bindRepository(repository: Repository): IRepository

    @Binds
    abstract fun provideRemoteSource(remoteSource: RemoteSource): IRemoteSource

    @Binds
    abstract fun provideLocalSource(localDataSource: LocalDataSource): ILocalDataSource

}