package me.alberto.cowrywiseconveter.screens.home.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import me.alberto.cowrywiseconveter.data.domain.usecase.ConvertUserCase
import me.alberto.cowrywiseconveter.data.domain.usecase.GetSymbolsFromRemoteUseCase
import me.alberto.cowrywiseconveter.data.domain.usecase.GetSymbolsUserCase
import me.alberto.cowrywiseconveter.data.remote.model.Query
import me.alberto.cowrywiseconveter.data.remote.source.Result
import me.alberto.cowrywiseconveter.util.LoadingState
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getSymbolsUserCase: GetSymbolsUserCase,
    private val convertUserCase: ConvertUserCase,
    private val getRemoteSymbolsUserCase: GetSymbolsFromRemoteUseCase
) :
    ViewModel() {

    val symbols = getSymbolsUserCase.execute()

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState> = _loadingState

    val convertText = MutableLiveData<String>()
    val conversionResult = MutableLiveData<String>()

    val baseCurrency = MutableLiveData<String>()
    val targetCurrency = MutableLiveData<String>()

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    val showHistory: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        fun update() {
            baseCurrency.value ?: return
            targetCurrency.value ?: return
            value = (!baseCurrency.value.isNullOrEmpty() && !targetCurrency.value.isNullOrEmpty())
        }
        addSource(baseCurrency) { update() }
        addSource(targetCurrency) { update() }
        update()
    }


    fun getSymbols() {
        viewModelScope.launch {
            try {
                _loadingState.value = LoadingState.Loading
                when (val result = getRemoteSymbolsUserCase.execute()) {
                    is Result.Success -> {
                        _loadingState.value = LoadingState.Success
                    }
                    is Result.Failure -> {
                        _error.value = result.error
                        _loadingState.value = LoadingState.Error(result.error)
                    }
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
                _error.value = exception.message
                _loadingState.value = LoadingState.Error("Network error. Please try again")
            }
        }
    }

    fun convert() {
        var message = ""
        if (convertText.value.isNullOrEmpty()) {
            message += "value to convert is empty, "
            _error.postValue(message)
        }

        if (baseCurrency.value.isNullOrEmpty()) {
            message += "convert from value is empty "
            _error.postValue(message)
        }

        if (targetCurrency.value.isNullOrEmpty()) {
            message += "convert to value is empty "
            _error.postValue(message)
        }

        if (!convertText.value.isNullOrEmpty() && !baseCurrency.value.isNullOrEmpty() && !targetCurrency.value.isNullOrEmpty()) {
            viewModelScope.launch {
                try {
                    _loading.postValue(true)
                    val query = Query(
                        baseCurrency.value.toString(),
                        targetCurrency.value.toString(),
                        convertText.value!!.toDouble()
                    )
                    when (val result = convertUserCase.execute(query)) {
                        is Result.Success -> {
                            conversionResult.value = result.data.toString()
                            _loading.postValue(false)
                        }
                        is Result.Failure -> {
                            _error.value = result.error
                            _loading.postValue(false)
                        }
                    }

                } catch (exp: Exception) {
                    _error.value = exp.message
                    _loading.postValue(false)
                }

            }

        }

    }

}