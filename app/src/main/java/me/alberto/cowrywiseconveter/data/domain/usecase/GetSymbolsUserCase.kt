package me.alberto.cowrywiseconveter.data.domain.usecase

import androidx.lifecycle.LiveData
import me.alberto.cowrywiseconveter.data.domain.model.Country
import me.alberto.cowrywiseconveter.data.domain.repository.IRepository
import me.alberto.cowrywiseconveter.data.repository.Repository
import me.alberto.cowrywiseconveter.data.remote.source.Result
import javax.inject.Inject

class GetSymbolsUserCase @Inject constructor(private val repository: IRepository) :
    UniqueUseCase<LiveData<List<Country>>, Nothing>() {
    override fun buildUseCase(parms: Nothing?): LiveData<List<Country>> {
        return repository.getSymbols()
    }
}