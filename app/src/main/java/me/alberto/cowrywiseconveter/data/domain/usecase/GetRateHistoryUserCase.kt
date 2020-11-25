package me.alberto.cowrywiseconveter.data.domain.usecase

import me.alberto.cowrywiseconveter.data.domain.repository.IRepository
import me.alberto.cowrywiseconveter.data.remote.model.HistoryQuery
import me.alberto.cowrywiseconveter.data.remote.source.Result
import me.alberto.cowrywiseconveter.data.repository.Repository
import javax.inject.Inject

class GetRateHistoryUserCase @Inject constructor(private val repository: IRepository) :
    UseCase<Result<Map<String, Double>>, HistoryQuery>() {
    override suspend fun buildUseCase(params: HistoryQuery?): Result<Map<String, Double>> {
        requireNotNull(params) { "{params cannot be null" }
        return repository.getHistoryRates(
            params.startDate,
            params.endDate,
            params.base,
            params.symbol
        )
    }
}