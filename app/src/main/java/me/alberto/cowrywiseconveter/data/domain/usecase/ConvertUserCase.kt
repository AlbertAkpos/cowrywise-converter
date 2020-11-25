package me.alberto.cowrywiseconveter.data.domain.usecase

import me.alberto.cowrywiseconveter.data.domain.repository.IRepository
import me.alberto.cowrywiseconveter.data.remote.model.Query
import me.alberto.cowrywiseconveter.data.remote.source.Result
import javax.inject.Inject

class ConvertUserCase @Inject constructor(private val repository: IRepository) : UseCase<Result<Double>, Query>() {
    override suspend fun buildUseCase(params: Query?): Result<Double> {
        requireNotNull(params){"Params  cannot be null"}
        return repository.convert(params.from, params.to, params.amount)
    }
}