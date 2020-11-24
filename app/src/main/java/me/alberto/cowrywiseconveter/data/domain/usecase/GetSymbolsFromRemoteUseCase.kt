package me.alberto.cowrywiseconveter.data.domain.usecase

import me.alberto.cowrywiseconveter.data.remote.source.Result
import me.alberto.cowrywiseconveter.data.repository.Repository
import javax.inject.Inject

class GetSymbolsFromRemoteUseCase @Inject constructor (private val repository: Repository): UseCase<Result<Boolean>, Nothing>() {
    override suspend fun buildUseCase(params: Nothing?):Result<Boolean>  {
        return repository.getSymbolsFromRemote()
    }
}