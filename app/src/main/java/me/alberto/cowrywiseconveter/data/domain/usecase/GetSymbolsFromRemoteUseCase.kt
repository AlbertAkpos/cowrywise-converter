package me.alberto.cowrywiseconveter.data.domain.usecase

import me.alberto.cowrywiseconveter.data.domain.repository.IRepository
import me.alberto.cowrywiseconveter.data.remote.source.Result
import javax.inject.Inject

class GetSymbolsFromRemoteUseCase @Inject constructor (private val repository: IRepository): UseCase<Result<Boolean>, Nothing>() {
    override suspend fun buildUseCase(params: Nothing?):Result<Boolean>  {
        return repository.getSymbolsFromRemote()
    }
}