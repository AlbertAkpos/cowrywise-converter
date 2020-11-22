package me.alberto.cowrywiseconveter.data.domain.usecase

import me.alberto.cowrywiseconveter.data.repository.Repository
import me.alberto.cowrywiseconveter.data.remote.source.Result
import javax.inject.Inject

class GetSymbolsUserCase @Inject constructor(private val repository: Repository) :
    UseCase<Result<List<String>>, Nothing>() {
    override suspend fun buildUseCase(params: Nothing?): Result<List<String>> {
        return repository.getSymbols()
    }
}