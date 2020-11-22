package me.alberto.cowrywiseconveter.data.repository

import me.alberto.cowrywiseconveter.data.domain.repository.IRepository
import me.alberto.cowrywiseconveter.data.remote.source.RemoteSource
import me.alberto.cowrywiseconveter.data.remote.source.Result
import javax.inject.Inject

class Repository @Inject constructor(private val remoteSource: RemoteSource) : IRepository {
    override suspend fun getSymbols(): Result<List<String>> {
        return remoteSource.getSymbols()
    }

    override suspend fun convert(from: String, to: String, amount: Long): Result<Double> {
        return remoteSource.convert(from, to, amount)
    }
}